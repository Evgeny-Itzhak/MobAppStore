package com.home.springtraining.controllers;

import com.home.springtraining.persistence.Application;
import com.home.springtraining.persistence.Category;
import com.home.springtraining.repository.ApplicationRepository;
import com.home.springtraining.repository.CategoryRepository;
import com.home.springtraining.utils.ArchiveParser;
import com.home.springtraining.utils.UploadFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

@Controller
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    private ArchiveParser archiveParser;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String getUploadApplicationPage(Model model) {

        Iterable<Category> categories = categoryRepository.findAll();

        model.addAttribute("application", new Application());
        model.addAttribute("categories", categories);

        log.trace("getUploadApplicationPage()");

        return "uploadform";
    }


    @RequestMapping(value = "/uploadApplication", method = RequestMethod.POST)
    public String uploadApplication(@Valid Application application, BindingResult result, Model model,
                                    @RequestParam("categoryId") Long categoryId,
                                    @RequestParam("applicationZipMultipart") MultipartFile applicationZipMultipart,
                                    @RequestParam("image512") MultipartFile image512,
                                    @RequestParam("image128") MultipartFile image128) throws IOException {

        log.trace("uploadApplication()");

        boolean uploadedImage512isValid = false;
        boolean uploadedImage128isValid = false;

        try {

            if (image512.getSize() > 0) {
                uploadedImage512isValid = UploadFileValidator.validateImage(image512);
            } else {
                log.info("Image 512 is not valid. Default image will be uploaded");
            }
            if (image128.getSize() > 0) {
                uploadedImage128isValid = UploadFileValidator.validateImage(image128);
            } else {
                log.info("Image 128 is not valid. Default image will be uploaded");
            }

            ZipFile applicationZip = UploadFileValidator.convertMultipartToZip(applicationZipMultipart);

            boolean zipFileValid = UploadFileValidator.validateZipEntries(applicationZip);

            Category category = categoryRepository.findOne(categoryId);

            if (zipFileValid) {
                archiveParser = new ArchiveParser(applicationZip);
                List<ZipEntry> zipEntryList = archiveParser.getFilesFromZip();
                ZipEntry zipEntry = null;

                if (zipEntryList.size() == 1) {
                    zipEntry = zipEntryList.get(0);
                }

                application.setCategory(category);
                application.setApplicationArchive(applicationZipMultipart.getBytes());
                application.setApplicationPackage(archiveParser.getApplicationPackage(zipEntry));

                if (uploadedImage512isValid) {
                    application.setApplicationImage512(image512.getBytes());
                }
                if (uploadedImage128isValid) {
                    application.setApplicationImage128(image128.getBytes());
                }

            } else {
                return "redirect:error";
            }

            if (result.hasErrors()) {
                log.info("error with uploading apllication =>  = [{}]", application.toString());
                return "uploadform";
            } else {
                applicationRepository.save(application);
                log.info("application uploaded = [{}]", application.toString());
            }

        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", e.getCause().getCause().getMessage());
            log.trace("error with application saving =>  = [{}]", e.getStackTrace());
            return "redirect:error";
        } catch (ZipException e) {
            model.addAttribute("errorMessage", "Application archive is not valid. Please, select \".zip\" file.");
            e.printStackTrace();
            log.trace("error with application saving =>  = [{}]", e.getStackTrace());
            return "redirect:error";
        } catch (IOException e) {
            model.addAttribute("errorMessage", e.getCause().getCause().getMessage());
            e.printStackTrace();
            log.trace("error with application saving =>  = [{}]", e.getStackTrace());
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", "Application archive entries are not valid. Please, select correct archive.");
            e.printStackTrace();
            log.trace("error with application saving =>  = [{}]", e.getStackTrace());
            return "redirect:error";
        }

        return "redirect:home";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getErrorPage(Model model, @RequestParam String errorMessage) {

        if (StringUtils.isEmpty(errorMessage)) {
            errorMessage = "Error during uploading application. Please try again and check all fields are filled.";
        }

        log.trace("getErrorPage() => message = [{}]", errorMessage);

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

}
