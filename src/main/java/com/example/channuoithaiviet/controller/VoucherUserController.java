package com.example.channuoithaiviet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.channuoithaiviet.entity.VoucherUser;
import com.example.channuoithaiviet.model.request.CreateVoucherUserRequest;
import com.example.channuoithaiviet.service.VoucherUserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/voucheruser")
@CrossOrigin(origins = "*",maxAge = 3600)
public class VoucherUserController {
	@Autowired
    private VoucherUserService voucherUserService;
	@PostMapping("/create")
    @Operation(summary="Tạo mới voucheruser")
    public ResponseEntity<VoucherUser> createProduct(@RequestBody CreateVoucherUserRequest request){
    	VoucherUser voucherUser = voucherUserService.createVoucherUser(request);
        return ResponseEntity.ok(voucherUser);
    }
}
