package com.example.channuoithaiviet.service;

import java.util.List;

import com.example.channuoithaiviet.entity.OrderDetail;

public interface OderdetailService {
    List<OrderDetail> getOrderDetailsByOrderId(long id);
    List<OrderDetail> getList();

}
