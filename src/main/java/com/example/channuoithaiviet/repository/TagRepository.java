package com.example.channuoithaiviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.channuoithaiviet.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    
}
