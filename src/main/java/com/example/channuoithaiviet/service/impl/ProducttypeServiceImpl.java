package com.example.channuoithaiviet.service.impl;


import java.util.List;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Producttype;
import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateProducttypeRequest;
import com.example.channuoithaiviet.repository.ProducttypeRepository;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.ProducttypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
@Service
public class ProducttypeServiceImpl implements ProducttypeService {

    @Autowired
    private ProducttypeRepository producttypeRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    // tất cả
    public List<Producttype> getList() {
        return producttypeRepository.findAll(Sort.by("id").descending());
    }
    // theo user
    @Override
    public List<Producttype> getListtypeByUser(long id){
        List<Producttype> list =producttypeRepository.getListtypeByUser(id);
        return list;
    }
    //
    @Override
    public Producttype createProducttype(CreateProducttypeRequest request) {
    	Producttype producttype = new Producttype();
    	producttype.setName(request.getName());
    	User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
    	producttype.setUser(user);
    	producttypeRepository.save(producttype);
        return producttype;
    }
    @Override
    public Producttype updateProducttype(long id, CreateProducttypeRequest request) {
        // TODO Auto-generated method stub

    	Producttype producttype = producttypeRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
    	producttype.setName(request.getName());
        producttypeRepository.save(producttype);
        return producttype;
    }

    @Override
    public void deleteProducttype(long id) {
        // TODO Auto-generated method stub
    	Producttype producttype = producttypeRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
    	producttypeRepository.delete(producttype);
    }

}
