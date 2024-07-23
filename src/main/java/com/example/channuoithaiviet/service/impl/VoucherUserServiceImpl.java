package com.example.channuoithaiviet.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.VoucherUser;
import com.example.channuoithaiviet.model.request.CreateVoucherUserRequest;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.repository.VoucherRepository;
import com.example.channuoithaiviet.repository.VoucherUserRepository;
import com.example.channuoithaiviet.service.VoucherUserService;

@Service
public class VoucherUserServiceImpl implements VoucherUserService {

	@Autowired
    private VoucherUserRepository voucherUserRepository;
	@Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserRepository userRepository;
	@Override
    public VoucherUser createVoucherUser(CreateVoucherUserRequest request) {
        VoucherUser voucherUser = new VoucherUser();
        // Set Voucher
        voucherRepository.findById(request.getVoucherId()).ifPresent(voucherUser::setVoucher);
        // Set User
        userRepository.findById(request.getUserId()).ifPresent(voucherUser::setUser);
        // Set CreateAt
        LocalDateTime createdAt = request.getCreateAt();
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        voucherUser.setCreateAt(createdAt);
        // Save and return
        return voucherUserRepository.save(voucherUser);
    }
}
