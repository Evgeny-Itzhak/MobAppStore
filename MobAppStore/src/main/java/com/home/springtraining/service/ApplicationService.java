package com.home.springtraining.service;

import com.home.springtraining.persistence.Application;
import com.home.springtraining.persistence.Category;

import java.util.List;

public interface ApplicationService {

    Application getApplicationByApplicationId(Long applicationId);

    Application getApplicationByApplicationName(String applicationName);

    List<Application> getApplicationsByCategory(Category category);

    Application addApplicationInDb(Application application);

    List<Application> getAllApplicationsOrderByApplicationDownloadsCountDesc();

}
