package com.home.springtraining.controllers;

import com.home.springtraining.persistence.Application;
import com.home.springtraining.persistence.Category;
import com.home.springtraining.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    private Category category;
    private List<Application> applicationList;
    private List<Application> applicationsTop5List;

    @RequestMapping(value = "/showApplicationsByCategory")
    public String displayApplicationsOfSelectedCategory(@RequestParam("categoryId") Long categoryId, Model model) {

        category = new Category();
        category.setCategoryId(categoryId);

        applicationList = applicationService.getApplicationsByCategory(category);
        applicationsTop5List = applicationService.getAllApplicationsOrderByApplicationDownloadsCountDesc();

        model.addAttribute("applications", applicationList);
        model.addAttribute("applicationsTop5", applicationsTop5List);

        if (applicationList.size() == 0) {
            model.addAttribute("applications", new ArrayList<>());
        }
        if (applicationsTop5List.size() == 0) {
            model.addAttribute("applicationsTop5", new ArrayList<>());
        }

        return "home";
    }

    @RequestMapping(value = "/showApplication")
    public String displayApplication(@RequestParam("applicationId") String applicationId, Model model) {

        if (!applicationId.matches("\\d+")) {
            model.addAttribute("errorMessage", applicationId + " is not a correct applicationId");
            return "redirect:error";
        }

        Application application = applicationService.getApplicationByApplicationId(Long.valueOf(applicationId));

        if (application == null) {

            model.addAttribute("errorMessage", "there is no application with applicationId = " + applicationId);

            return "redirect:error";

        } else {
            log.trace("show application = [{}]", application.toString());
        }

        model.addAttribute("application", application);

        return "application";
    }

}
