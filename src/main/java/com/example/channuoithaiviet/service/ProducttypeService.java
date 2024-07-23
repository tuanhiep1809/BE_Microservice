package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.Producttype;
import com.example.channuoithaiviet.model.request.CreateProducttypeRequest;

public interface ProducttypeService {
	List<Producttype> getList();  // lấy hết
	List<Producttype> getListtypeByUser(long id); // láy theo user
	Producttype createProducttype(CreateProducttypeRequest request);//tạo mới
	Producttype updateProducttype(long id, CreateProducttypeRequest request); //sửa
	 void deleteProducttype(long id);  //xóa

}
