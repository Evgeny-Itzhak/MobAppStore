package com.home.springtraining.repository;

import com.home.springtraining.persistence.Application;
import com.home.springtraining.persistence.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    Application findApplicationByApplicationName(String applicationName);

    List<Application> findApplicationsByCategory(Category category);

    List<Application> findApplicationsByOrderByApplicationDownloadsCountDesc();

}
