package com.home.springtraining.controllers;

import com.home.springtraining.persistence.Application;
import com.home.springtraining.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileDownloadController {

    private static final Logger log = LoggerFactory.getLogger(FileDownloadController.class);

    @Autowired
    ApplicationRepository applicationRepository;

    @RequestMapping(value = "/downloadApplication", method = RequestMethod.GET)
    public String downloadApplication(@RequestParam("applicationId") Long applicationId, HttpServletResponse response) {

        Application application = applicationRepository.findOne(applicationId);
        application.setApplicationDownloadsCount(application.getApplicationDownloadsCount() + 1);

        byte[] applicationZip = application.getApplicationArchive();

        response.setContentType("application/zip");
        response.setContentLength(applicationZip.length);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + application.getApplicationName() + "\\.zip\"");

        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(applicationZip);
            servletOutputStream.flush();
            servletOutputStream.close();
            applicationRepository.save(application);
            log.info("application saved = [{}]", application.toString());

        } catch (IOException e) {
            e.printStackTrace();
            log.info("application saved", application.toString());
        }

        return "home";
    }
}
