package com.example.channuoithaiviet.model.response;

import java.util.List;
import java.util.Set;

import com.example.channuoithaiviet.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
	
    private long id;

    private String username;

    private String email;

    private List<String> roles;
}
