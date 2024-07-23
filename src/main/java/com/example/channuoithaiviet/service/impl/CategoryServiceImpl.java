package com.example.channuoithaiviet.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Category;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateCategoryRequest;
import com.example.channuoithaiviet.repository.CategoryRepository;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        List<Category> list = categoryRepository.findAll(Sort.by("id").descending());
        return list;
    }
    @Override
    public Map<Long, Long> countProductsByCategory() {
        List<Object[]> results = categoryRepository.countProductsByCategory();
        Map<Long, Long> productCounts = new HashMap<>();
        for (Object[] result : results) {
            Long categoryId = (Long) result[0];
            Long count = (Long) result[1];
            productCounts.put(categoryId, count);
        }
        return productCounts;
    }

    @Override
    public Category createCategory(CreateCategoryRequest request) {
        // TODO Auto-generated method stub
        Category category = new Category();
        category.setName(request.getName());
        category.setLink(request.getLink());
        category.setEnable(false);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(long id, CreateCategoryRequest request) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
        category.setName(request.getName());
        category.setLink(request.getLink());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void enableCategory(long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
        if(category.isEnable()){
            category.setEnable(false);
        } else{
            category.setEnable(true);
        }
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Category With Id: " + id));
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getListEnabled() {
        // TODO Auto-generated method stub
        List<Category> list = categoryRepository.findALLByEnabled();
        return list;
    }
    
}
