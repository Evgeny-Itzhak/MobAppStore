package com.home.springtraining.controllers;

import com.home.springtraining.persistence.Application;
import com.home.springtraining.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    ApplicationService applicationService;

    Application application;

    @RequestMapping(value = "/showImage128")
    public byte[] showImage128(@RequestParam("applicationId") Long applicationId, Model model) {

        application = applicationService.getApplicationByApplicationId(applicationId);

        byte[] image128InByte = application.getApplicationImage128();

        return image128InByte;
    }

    @RequestMapping(value = "/showImage512")
    public byte[] showImage512(@RequestParam("applicationId") Long applicationId, Model model) {

        application = applicationService.getApplicationByApplicationId(applicationId);

        byte[] image512InByte = application.getApplicationImage512();

        return image512InByte;
    }

}
