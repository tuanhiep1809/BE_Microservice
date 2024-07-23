package com.example.channuoithaiviet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Role;
import com.example.channuoithaiviet.repository.RoleRepository;
import com.example.channuoithaiviet.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
    private RoleRepository roleRepository;
	
	 @Override
	    public List<Role> getListrole() {
	        // TODO Auto-generated method stub
	        return roleRepository.findAll(Sort.by("id").descending());
	    }
}
