package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.model.request.ChangePasswordRequest;
import com.example.channuoithaiviet.model.request.CreateUserRequest;
import com.example.channuoithaiviet.model.request.UpdateProfileRequest;

public interface UserService {
    
    void register(CreateUserRequest request);


    User getUserByUsername(String username);

    User updateUser(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);
    String verifyAccount(String email, String otp); 
    String regenerateOtp(String email); // Add this method to the interface
    // 
    List<User> getListuser();
    void deleleUser(long id);
    User getUser(long id);
    User updatesUser(long id,CreateUserRequest request);
}
