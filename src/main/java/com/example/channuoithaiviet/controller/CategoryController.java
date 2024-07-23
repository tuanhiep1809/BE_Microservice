package com.example.channuoithaiviet.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.channuoithaiviet.entity.Category;
import com.example.channuoithaiviet.model.request.CreateCategoryRequest;
import com.example.channuoithaiviet.model.response.MessageResponse;
import com.example.channuoithaiviet.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @Operation(summary="Lấy danh sách danh mục")
    public ResponseEntity<?> getListCategory(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/product-count")
    @Operation(summary="Lấy số lượng sản phẩm theo danh mục")
    public ResponseEntity<Map<Long, Long>> getProductCountByCategory() {
        Map<Long, Long> productCounts = categoryService.countProductsByCategory();
        return ResponseEntity.ok(productCounts);
    }
    
    @GetMapping("/enabled")
    @Operation(summary="Lấy ra danh sách danh mục đã kích hoạt")
    public ResponseEntity<List<Category>> getListEnabled(){
        List<Category> categories = categoryService.getListEnabled();
        return ResponseEntity.ok(categories);
    }
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    @Operation(summary="Tạo mới danh mục")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.createCategory(request);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm danh mục bằng id và cập nhật danh mục đó")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/enable/{id}")
    @Operation(summary="Kích hoạt danh mục bằng id")
    public ResponseEntity<?> enabled(@PathVariable long id){
        categoryService.enableCategory(id);
        return ResponseEntity.ok(new MessageResponse("Cập nhật thành công"));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa danh mục bằng id")
    public ResponseEntity<?> delete(@PathVariable long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new MessageResponse("Xóa thành công"));
    }


}
