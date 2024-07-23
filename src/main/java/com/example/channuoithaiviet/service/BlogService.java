package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.Blog;
import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.model.request.CreateBlogRequest;

public interface BlogService {
    
//    List<Blog> getList(int page, int size);
	 List<Blog> getList();
    List<Blog> getListNewest(int limit);

    Blog getBlog(long id);

    Blog createBlog(CreateBlogRequest request);

    Blog updateBlog(long id,CreateBlogRequest request);

    void deleteBlog(long id);
    List<Blog> getListBlogByUser(long id);
    long getTotalBlogs(); // tính tổng sản phẩm
}
