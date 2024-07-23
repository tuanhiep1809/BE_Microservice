package com.example.channuoithaiviet.service;
import java.util.List;

import com.example.channuoithaiviet.entity.Productsize;
import com.example.channuoithaiviet.model.request.CreateProductsizeRequest;
public interface ProductsizeService {
	List<Productsize> getList();  // lấy hết
	List<Productsize> getListsizeByUser(long id); // láy theo user
	Productsize createProductsize(CreateProductsizeRequest request);//tạo mới
	Productsize updateProductsize(long id, CreateProductsizeRequest request); //sửa
	 void deleteProductsize(long id);  //xóa

}
