package com.example.channuoithaiviet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.About;
import com.example.channuoithaiviet.entity.Image;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateAboutRequest;
import com.example.channuoithaiviet.repository.AboutRepository;
import com.example.channuoithaiviet.repository.ImageRepository;
import com.example.channuoithaiviet.service.AboutService;

@Service
public class AboutServiceImpl implements AboutService {
	 @Autowired
	 private AboutRepository aboutRepository;
	 @Autowired
	    private ImageRepository imageRepository;
	 @Override
	    public List<About> getListAbout() {
	        // TODO Auto-generated method stub
	        return aboutRepository.findAll();
	    }

	 @Override
	    public About updateAbout(CreateAboutRequest request) {
	        // Lấy tất cả các thiết lập từ cơ sở dữ liệu (trong trường hợp chỉ có một thiết lập)
	        List<About> abouts = aboutRepository.findAll();
	        if (abouts.isEmpty()) {
	            throw new NotFoundException("Không tìm thấy thiết lập để cập nhật");
	        }

	        // Lấy thiết lập duy nhất (trong trường hợp chỉ có một thiết lập)
	        About about = abouts.get(0);

	        // Cập nhật thông tin mới
	        about.setTitle(request.getTitle());
	        about.setNoidung(request.getNoidung());
	        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
	        about.setImage(image);

	        // Lưu lại thiết lập đã cập nhật vào cơ sở dữ liệu
	        aboutRepository.save(about);
	        return about;
	    }
}

