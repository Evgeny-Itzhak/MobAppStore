package com.home.springtraining.service.serviceimpl;

import com.home.springtraining.persistence.Application;
import com.home.springtraining.persistence.Category;
import com.home.springtraining.repository.ApplicationRepository;
import com.home.springtraining.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public Application getApplicationByApplicationId(Long applicationId) {
        return applicationRepository.findOne(applicationId);
    }

    @Override
    public Application getApplicationByApplicationName(String applicationName) {
        return applicationRepository.findApplicationByApplicationName(applicationName);
    }

    @Override
    public List<Application> getApplicationsByCategory(Category category) {
        List<Application> applicationsByCategory = applicationRepository.findApplicationsByCategory(category);

        if (applicationsByCategory.isEmpty()) {
            log.info("There is no application in category with categoryId = [{}]", category.getCategoryId());
        } else {
            log.info("Show applications in category with categoryId = [{}]", category.getCategoryId());

            for (Application application: applicationsByCategory){
                log.trace("show application = [{}]", application.toString());
            }
        }
        return applicationsByCategory;
    }

    @Override
    public Application addApplicationInDb(Application application) {

        log.trace("addApplicationInDb({})", application.toString());

        Application savedApplication = applicationRepository.save(application);

        if (savedApplication != null){
            log.trace("Application = [{}] added successfully", application.toString());
        }

        return savedApplication;
    }

    @Override
    public List<Application> getAllApplicationsOrderByApplicationDownloadsCountDesc() {
        return applicationRepository.findApplicationsByOrderByApplicationDownloadsCountDesc();
    }
}
