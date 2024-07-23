package com.example.channuoithaiviet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Productsize;
import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateProductsizeRequest;
import com.example.channuoithaiviet.repository.ProductsizeRepository;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.ProductsizeService;
@Service
public class ProductsizeServiceImpl implements ProductsizeService {

    @Autowired
    private ProductsizeRepository productsizeRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    // tất cả
    public List<Productsize> getList() {
        return productsizeRepository.findAll(Sort.by("id").descending());
    }
    // theo user
    @Override
    public List<Productsize> getListsizeByUser(long id){
        List<Productsize> list =productsizeRepository.getListSizeByUser(id);
        return list;
    }
    //
    @Override
    public Productsize createProductsize(CreateProductsizeRequest request) {
    	Productsize productsize = new Productsize();
    	productsize.setName(request.getName());
    	User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
    	productsize.setUser(user);
    	productsizeRepository.save(productsize);
        return productsize;
    }
    @Override
    public Productsize updateProductsize(long id, CreateProductsizeRequest request) {
        // TODO Auto-generated method stub

    	Productsize productsize = productsizeRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
    	productsize.setName(request.getName());
    	productsizeRepository.save(productsize);
        return productsize;
    }

    @Override
    public void deleteProductsize(long id) {
        // TODO Auto-generated method stub
    	Productsize productsize = productsizeRepository.findById(id).orElseThrow(()-> new NotFoundException("Not Foud Tag"));
    	productsizeRepository.delete(productsize);
    }

}

