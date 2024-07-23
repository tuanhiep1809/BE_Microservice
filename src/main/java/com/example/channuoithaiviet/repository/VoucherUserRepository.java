package com.example.channuoithaiviet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.channuoithaiviet.entity.VoucherUser;

@Repository
public interface VoucherUserRepository  extends JpaRepository<VoucherUser,Long> {

}
