package com.example.channuoithaiviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.channuoithaiviet.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    
}