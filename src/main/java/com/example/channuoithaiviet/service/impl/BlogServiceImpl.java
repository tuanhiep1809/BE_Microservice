package com.example.channuoithaiviet.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Blog;
import com.example.channuoithaiviet.entity.Image;
import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.entity.Tag;
import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateBlogRequest;
import com.example.channuoithaiviet.repository.BlogRepository;
import com.example.channuoithaiviet.repository.ImageRepository;
import com.example.channuoithaiviet.repository.TagRepository;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.BlogService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Blog> getList() {
        // TODO Auto-generated method stub
        return blogRepository.findAll(Sort.by("id").descending());
    }
//    @Override
//    public List<Blog> getList(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Blog> blogPage = blogRepository.findAll(pageable);
//        return blogPage.getContent();
//    }
    @Override
    public Blog getBlog(long id){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        return blog;
    }
    @Override
    public List<Blog> getListBlogByUser(long id){
        List<Blog> list =blogRepository.getListBlogByUser(id);
        return list;
    }
    @Override
    public Blog createBlog(CreateBlogRequest request) {
        // TODO Auto-generated method stub
        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        blog.setImage(image);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
        blog.setUser(user);
        blog.setCreateAt(new Timestamp(System.currentTimeMillis()));
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        blog.setTags(tags);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Blog updateBlog(long id, CreateBlogRequest request) {
        // TODO Auto-generated method stub
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        blog.setImage(image);
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        blog.setTags(tags);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public void deleteBlog(long id) {
        // TODO Auto-generated method stub
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blog.getTags().remove(this);
        blogRepository.delete(blog);
    }

    @Override
    public List<Blog> getListNewest(int limit) {
        // TODO Auto-generated method stub
        List<Blog> list = blogRepository.getListNewest(limit);
        return list;
    }
    @Override
    public long getTotalBlogs() {
        return blogRepository.count();
    }

}
