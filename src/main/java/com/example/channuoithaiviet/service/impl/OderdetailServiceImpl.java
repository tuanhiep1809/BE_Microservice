package com.example.channuoithaiviet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.OrderDetail;
import com.example.channuoithaiviet.repository.OrderDetailRepository;
import com.example.channuoithaiviet.service.OderdetailService;

@Service
public class OderdetailServiceImpl implements OderdetailService{

	@Autowired
    private OrderDetailRepository orderdetailRepository;
	
	@Override
    public List<OrderDetail> getOrderDetailsByOrderId(long id){
        List<OrderDetail> list =orderdetailRepository.getOrderDetailsByOrderId(id);
        return list;
    }
	@Override
    public List<OrderDetail> getList() {
        return orderdetailRepository.findAll(Sort.by("id").descending());
    }
}
