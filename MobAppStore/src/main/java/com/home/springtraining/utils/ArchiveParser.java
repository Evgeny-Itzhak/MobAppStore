package com.home.springtraining.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ArchiveParser {

    private ZipFile zipFile;

    public ArchiveParser(ZipFile zipFile) {
        this.zipFile = zipFile;
    }

    public List<ZipEntry> getFilesFromZip() {
        List zipEntries = new ArrayList();
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            zipEntries.add(entries.nextElement());
        }
        return zipEntries;
    }

    public Map<String, String> getAppProps(ZipEntry zipEntry) {
        Map<String, String> propsMap;
        try (InputStream zipInput = zipFile.getInputStream(zipEntry)) {
            Properties appProps = new Properties();
            appProps.load(zipInput);
            propsMap = new HashMap<>();
            propsMap.put("name", appProps.getProperty("name"));
            propsMap.put("package", appProps.getProperty("package"));
            System.out.println("name = " + appProps.getProperty("name"));
            System.out.println("package = " + appProps.getProperty("package"));
            return propsMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public String getApplicationPackage(ZipEntry zipEntry){
        Map<String, String> propsMap = this.getAppProps(zipEntry);
        return propsMap.get("package");
    }
}