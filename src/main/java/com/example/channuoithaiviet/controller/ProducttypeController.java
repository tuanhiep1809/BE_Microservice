package com.example.channuoithaiviet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.channuoithaiviet.entity.Producttype;
import com.example.channuoithaiviet.model.request.CreateProducttypeRequest;
import com.example.channuoithaiviet.model.response.MessageResponse;
import com.example.channuoithaiviet.service.ProducttypeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/producttype")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProducttypeController {
	@Autowired
    private ProducttypeService producttypeService;
	
	@GetMapping("/")
    @Operation(summary="Lấy tất cả danh sách")
    public ResponseEntity<List<Producttype>> getList(){
        List<Producttype> list = producttypeService.getList();

        return ResponseEntity.ok(list);

    }
	@GetMapping("/{id}")
    @Operation(summary="Lấy ra danh sách sản phẩm theo id người dùng")
    public ResponseEntity<List<Producttype>> getListtypeByUser(@PathVariable long id){
        List<Producttype> list =  producttypeService.getListtypeByUser(id);
        return ResponseEntity.ok(list);
    }
	@PostMapping("/create")
    @Operation(summary="Tạo mới sản phẩm color")
    public ResponseEntity<Producttype> createProducttype(@RequestBody CreateProducttypeRequest request){
		Producttype producttype = producttypeService.createProducttype(request);

        return ResponseEntity.ok(producttype);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm sản phẩm color bằng id và cập nhật sản phẩm đó")
    public ResponseEntity<Producttype> updateProducttype(@PathVariable long id,@RequestBody CreateProducttypeRequest request){
    	Producttype producttype = producttypeService.updateProducttype(id, request);

        return ResponseEntity.ok(producttype);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa sản phẩm color bằng id")
    public ResponseEntity<?> deleteProducttype(@PathVariable long id){
    	producttypeService.deleteProducttype(id);

        return ResponseEntity.ok(new MessageResponse("Product is d  elete"));
    }

}
