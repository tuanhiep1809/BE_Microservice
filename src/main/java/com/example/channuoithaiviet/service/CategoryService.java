package com.example.channuoithaiviet.service;

import java.util.List;
import java.util.Map;

import com.example.channuoithaiviet.entity.Category;
import com.example.channuoithaiviet.model.request.CreateCategoryRequest;

public interface CategoryService {
    List<Category> findAll();

    List<Category> getListEnabled();

    Category createCategory(CreateCategoryRequest request);

    Category updateCategory(long id,CreateCategoryRequest request);

    void enableCategory(long id);

    void deleteCategory(long id);
    Map<Long, Long> countProductsByCategory();
}
