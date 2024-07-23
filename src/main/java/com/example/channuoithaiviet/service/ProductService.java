package com.example.channuoithaiviet.service;

import java.util.List;
import java.util.Optional;

import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.model.request.CreateProductRequest;

public interface ProductService {
    
    List<Product> getList();  // lấy all

    List<Product> getListNewst(int number);  // lấy sản phẩm mới

    List<Product> getListByPrice();   // lấy theo giá

    List<Product> findRelatedProduct(long id);    

    List<Product> getListProductByCategory(long id);//lấy theo danh mục
    List<Product> getListProductByUser(long id); //lấy theo user

    List<Product> getListByPriceRange(long id,int min, int max); //tìm theo

    List<Product> searchProduct(String keyword);  ////tìm theo keywwork

    Product getProduct(long id);

    Product createProduct(CreateProductRequest request);

    Product updateProduct(long id, CreateProductRequest request);
    Product updateProductgiamgia(long id, CreateProductRequest request);

    void deleteProduct(long id);
    
    void updateProductQuantity(long id, int quantity ,int quantitybuy);
    long getTotalProducts(); // tính tổng sản phẩm
    long getTotalQuantitybuy(); // tính tổng sản phẩm đã bán
//    List<Product> getTopSellingProductsLast30Days();

   
}
