package com.example.channuoithaiviet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.channuoithaiviet.entity.Role;
import com.example.channuoithaiviet.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*",maxAge = 3600)
public class RoleController {
	@Autowired
    private RoleService roleService;
	
	@GetMapping("/")
    @Operation(summary="Lấy tất cả danh sách tất cả các quyền")
    public ResponseEntity<List<Role>> getListrole(){
        List<Role> list = roleService.getListrole();

        return ResponseEntity.ok(list);

    }
}
