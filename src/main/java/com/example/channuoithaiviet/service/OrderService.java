package com.example.channuoithaiviet.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.channuoithaiviet.entity.Order;
import com.example.channuoithaiviet.entity.Orderstatus;
import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.entity.Voucher;
import com.example.channuoithaiviet.model.request.CreateOrderRequest;

public interface OrderService {
    
    void placeOrder(CreateOrderRequest request);

    List<Order> getList();
    
    List<Order> getOrderByUser(String username);
    
//    ResponseEntity<?> checkOrder(String orderCode);
    Order checkOrder(String orderCode);

    ResponseEntity<?> removeOrder(String code);
    // update 
    Order updateOrder(long id, CreateOrderRequest request);
    Order updateOrderstatus(long id, CreateOrderRequest request);
    List<Orderstatus> getListstatus();
    
    List<Order> getListOrder(long id);
    Long getMaxOrderId();
    
    // thống kê
    List<Order> getListOrdercharts(int number);
    long getTotalOrders(); // tổng đơn hàng
    long calculateTotalRevenueByStatus();  // tỏng doanh thu


}
