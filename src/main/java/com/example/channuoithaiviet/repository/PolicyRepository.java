package com.example.channuoithaiviet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.channuoithaiviet.entity.Policy;
@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long> {
	@Query(value ="Select * from Policy where kieu = :id and enable = 1 ;",nativeQuery = true)
    List<Policy> getListPolicyBykieu(long id);
}
