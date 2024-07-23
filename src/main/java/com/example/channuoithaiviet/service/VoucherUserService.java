package com.example.channuoithaiviet.service;

import com.example.channuoithaiviet.entity.VoucherUser;
import com.example.channuoithaiviet.model.request.CreateVoucherUserRequest;

public interface VoucherUserService {
	VoucherUser createVoucherUser(CreateVoucherUserRequest request);
}
