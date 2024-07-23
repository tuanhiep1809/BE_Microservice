package com.example.channuoithaiviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.channuoithaiviet.entity.Orderstatus;

@Repository
public interface OrderstatusRepository extends JpaRepository<Orderstatus,Long> {

}
