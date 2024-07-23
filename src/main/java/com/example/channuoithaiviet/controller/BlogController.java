package com.example.channuoithaiviet.controller;

import org.springframework.http.ResponseEntity;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.channuoithaiviet.entity.Blog;
import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.model.request.CreateBlogRequest;
import com.example.channuoithaiviet.model.response.MessageResponse;
import com.example.channuoithaiviet.service.BlogService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*",maxAge = 3600)
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    @Operation(summary="Lấy tất cả danh sách blog")
    public ResponseEntity<List<Blog>> getList(){
        List<Blog> list = blogService.getList();

        return ResponseEntity.ok(list);

    }
//    @GetMapping
//    @Operation(summary="Lấy tất cả danh sách blog theo phân trang")
//    public List<Blog> getList(@RequestParam(defaultValue = "0") int page,
//                                @RequestParam(defaultValue = "10") int size) {
//        return blogService.getList(page, size);
//    }
    @GetMapping("/total")
    @Operation(summary = "Lấy tổng số lượng bài viết")
    public ResponseEntity<Long> getTotalBlogs() {
        long totalBlogs = blogService.getTotalBlogs();
        return ResponseEntity.ok(totalBlogs);
    }
    @GetMapping("/pageblog/{id}")
    @Operation(summary="Lấy ra danh sách blog theo id người dùng")
    public ResponseEntity<List<Blog>> getListBlogByUser(@PathVariable long id){
        List<Blog> list =  blogService.getListBlogByUser(id);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    @Operation(summary="Lấy ra blog bằng ID")
    public ResponseEntity<Blog> getBlog(@PathVariable long id){
        
        Blog blog =blogService.getBlog(id);
        return ResponseEntity.ok(blog);

    }

    @GetMapping("/newest")
    @Operation(summary="Lấy ra danh sách blog mới nhất với số lượng = limit")
    public ResponseEntity<List<Blog>> getListNewest(@RequestParam int limit){
        List<Blog> list = blogService.getListNewest(limit);
        return ResponseEntity.ok(list);
    }


    @PostMapping("/create")
    @Operation(summary="Tạo mới blog")
    public ResponseEntity<Blog> create(@RequestBody CreateBlogRequest request){

        Blog blog = blogService.createBlog(request);

        return ResponseEntity.ok(blog);

    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm blog bằng id và cập nhật blog đó")
    public ResponseEntity<Blog> update(@PathVariable long id, @RequestBody CreateBlogRequest request){

        Blog blog = blogService.updateBlog(id, request);

        return ResponseEntity.ok(blog);

    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa blog bằng Id")
    public ResponseEntity<?> delete(@PathVariable long id){
        blogService.deleteBlog(id);
        return ResponseEntity.ok(new MessageResponse("Delete success"));
    }
    
}
