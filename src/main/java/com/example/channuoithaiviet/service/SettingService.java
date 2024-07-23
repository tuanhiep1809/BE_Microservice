package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.Setting;
import com.example.channuoithaiviet.model.request.CreateSettingRequest;

public interface SettingService {
	List<Setting> getListSetting();
	Setting updateSetting(CreateSettingRequest request);
	
}
