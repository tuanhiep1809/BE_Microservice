package com.example.channuoithaiviet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Blog;
import com.example.channuoithaiviet.entity.Image;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.repository.ImageRepository;
import com.example.channuoithaiviet.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getListImage() {
        // TODO Auto-generated method stub
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(long id) {
        // TODO Auto-generated method stub
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found width id :" + id));

        return image;
    }

    @Override
    public Image save(Image image) {
        // TODO Auto-generated method stub
        return imageRepository.save(image);
    }

    @Override
    public List<Image> getListByUser(long userId) {
        // TODO Auto-generated method stub
        List<Image> images = imageRepository.getListImageOfUser(userId);
        return images;
    }

    @Override
    public void deleteImage(long id) {
        // TODO Auto-generated method stub
    	Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
     
    	imageRepository.delete(image);
        
    }
}
