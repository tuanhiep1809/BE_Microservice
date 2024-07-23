package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.Productcolor;
import com.example.channuoithaiviet.model.request.CreateProductcolorRequest;

public interface ProductcolorService {

	List<Productcolor> getList();  // lấy hết
	List<Productcolor> getListcolorByUser(long id); // láy theo user
	Productcolor createProductcolor(CreateProductcolorRequest request);//tạo mới
	Productcolor updateProductcolor(long id, CreateProductcolorRequest request); //sửa
	 void deleteProductcolor(long id);  //xóa
	
	
}
