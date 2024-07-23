package com.example.channuoithaiviet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.channuoithaiviet.entity.Producttype;

@Repository
public interface ProducttypeRepository extends JpaRepository<Producttype,Long> {
	  @Query(value ="Select * from Producttype where user_id = :id",nativeQuery = true)
	    List<Producttype> getListtypeByUser(long id);
}
