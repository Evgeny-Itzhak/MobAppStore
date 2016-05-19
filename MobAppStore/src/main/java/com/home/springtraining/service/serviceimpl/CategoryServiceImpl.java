package com.home.springtraining.service.serviceimpl;

import com.home.springtraining.persistence.Category;
import com.home.springtraining.repository.CategoryRepository;
import com.home.springtraining.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Iterable<Category> getAllCategories() {

        Iterable<Category> categories = categoryRepository.findAll();

        log.trace("display categories: getAllCategories()");

        for (Category category: categories){
            log.trace("category [ id = [{}], name = [{}] ]", category.getCategoryId(), category.getCategoryName());
        }

        return categories;
    }
}
