package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.About;
import com.example.channuoithaiviet.model.request.CreateAboutRequest;

public interface AboutService {
	List<About> getListAbout();
	About updateAbout(CreateAboutRequest request);
}
