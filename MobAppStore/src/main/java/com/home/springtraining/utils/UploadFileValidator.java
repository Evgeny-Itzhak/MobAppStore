package com.home.springtraining.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UploadFileValidator {

    public static boolean validateImage(MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            if (!multipartFile.getOriginalFilename().matches(".*(\\.jpe?g|\\.png)$")) {
                return false;
            } else {
                InputStream imageStream = multipartFile.getInputStream();
                BufferedImage image = ImageIO.read(imageStream);
                if (image.getWidth() == 128) {
                    return true;
                } else if (image.getHeight() == 512){
                    return true;
                }
                else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public static boolean validateZipEntries(ZipFile zipFile) throws IOException {

        ArchiveParser zipReaderJava = new ArchiveParser(zipFile);
        List<String> requiredKeys = new ArrayList<>(2);
        requiredKeys.add("name");
        requiredKeys.add("package");

        if (zipFile.size() != 1) {
            return false;
        }

        for (ZipEntry zipEntry : zipReaderJava.getFilesFromZip()) {
            if (!zipEntry.getName().matches(".*\\.txt$")) {
                return false;
            } else {

                Map<String, String> applicationProperties = zipReaderJava.getAppProps(zipEntry);

                if (applicationProperties.size() != 2) {
                    return false;
                } else {

                    ArrayList<String> keys = new ArrayList<>();
                    for (Map.Entry<String, String> entry : applicationProperties.entrySet()) {
                        String key = entry.getKey();
                        keys.add(key);
                    }
                    if (!keys.containsAll(requiredKeys)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static ZipFile convertMultipartToZip(MultipartFile multipart) throws IllegalStateException, IOException {

        File tempFile = File.createTempFile(multipart.getOriginalFilename(), null);
        multipart.transferTo(tempFile);

        ZipFile zipFile = new ZipFile(tempFile);
        return zipFile;
    }
}
