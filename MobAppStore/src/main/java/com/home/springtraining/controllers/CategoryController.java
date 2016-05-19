package com.home.springtraining.controllers;

import com.home.springtraining.service.ApplicationService;
import com.home.springtraining.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"categories","applicationsTop5"})
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApplicationService applicationService;

    @RequestMapping(value = "/")
    public String displayDefaultHomePage(Model model) {

        return "redirect:home";
    }

    @RequestMapping(value = "/home")
    public String displayHomePage(Model model) {

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("applicationsTop5", applicationService.getAllApplicationsOrderByApplicationDownloadsCountDesc());

        return "home";
    }

}
