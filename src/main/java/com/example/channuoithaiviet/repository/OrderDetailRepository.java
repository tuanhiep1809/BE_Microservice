package com.example.channuoithaiviet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.channuoithaiviet.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
	
	@Query(value ="Select * from Order_detail where order_id = :id ",nativeQuery = true)
    List<OrderDetail> getOrderDetailsByOrderId(long id);
}
