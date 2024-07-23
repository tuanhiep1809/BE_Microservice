package com.example.channuoithaiviet.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Blog;
import com.example.channuoithaiviet.entity.Category;
import com.example.channuoithaiviet.entity.Image;
import com.example.channuoithaiviet.entity.Order;
import com.example.channuoithaiviet.entity.OrderDetail;
import com.example.channuoithaiviet.entity.Orderstatus;
import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.entity.Voucher;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateOrderDetailRequest;
import com.example.channuoithaiviet.model.request.CreateOrderRequest;
import com.example.channuoithaiviet.model.request.CreateProductRequest;
import com.example.channuoithaiviet.repository.CategoryRepository;
import com.example.channuoithaiviet.repository.OrderDetailRepository;
import com.example.channuoithaiviet.repository.OrderRepository;
import com.example.channuoithaiviet.repository.OrderstatusRepository;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.OrderService;
import com.example.channuoithaiviet.util.EmailUtil;

import javax.mail.MessagingException;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
	  private EmailUtil emailUtil;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderstatusRepository orderstatusRepository;
    
    @Override
    public void placeOrder(CreateOrderRequest request) {
        // TODO Auto-generated method stub
        Order order = new Order();
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + request.getUsername()));
        order.setFirstname(request.getFirstname());
        order.setLastname(request.getLastname());
        order.setCountry(request.getCountry());
        order.setAddress(request.getAddress());
        order.setTown(request.getTown());
        order.setState(request.getState());
        order.setPostCode(request.getPostCode());
        order.setEmail(request.getEmail());
        order.setPhone(request.getPhone());
        order.setTotalPrice(request.getTotalPrice());
        order.setNote(request.getNote());   
        order.setSale(request.getSale());
        order.setOrderCode(request.getOrderCode());
        order.setCreateAt(new Timestamp(System.currentTimeMillis()));
        order.setOrderState(0);
//        Orderstatus orderstatus = orderstatusRepository.findById(request.getStatus()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getStatus()));
//    	order.setOrderstatus(orderstatus); 
    //    lấy mặc định bằng 1 để deploy
        Long defaultStatusId = Long.valueOf(1); // Chuyển đổi giá trị int thành Long
    	Orderstatus orderstatus = orderstatusRepository.findById(defaultStatusId).orElseThrow(() -> new NotFoundException("Not Found Category With Id: 1"));
    	order.setOrderstatus(orderstatus);
        order.setBank(request.getBank());
        orderRepository.save(order);
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<div style=\"font-family: Arial, sans-serif; padding: 20px;\">")
                    .append("<h2 style=\"text-align: center;\">Xác nhận đơn hàng</h2>")
                    .append("<p style=\"font-size: 16px;\">Xin chào ").append(order.getLastname()).append(",</p>")
                    .append("<p style=\"font-size: 14px;\">Cảm ơn bạn đã đặt hàng từ TAZASHOP của chúng tôi. Đơn hàng của bạn có mã: <strong>")
                    .append(order.getOrderCode()).append("</strong></p>")
                    .append("<div style=\"border: 1px solid #ccc; padding: 10px; margin-top: 10px;\">")
                    .append("<h3 style=\"text-align: center; font-size: 16px;\">Chi tiết đơn hàng</h3>")
                    .append("<table style=\"width: 100%; border-collapse: collapse; font-size: 14px;\">")
                    .append("<tr>")
                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Tên sản phẩm</th>")
                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Số lượng</th>")
                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Giá</th>")
                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Tổng</th>")
                    .append("</tr>");
        for(CreateOrderDetailRequest rq: request.getOrderDetails()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(rq.getName());
            orderDetail.setColor(rq.getColor());
            orderDetail.setSize(rq.getSize());
            orderDetail.setType(rq.getType());
            orderDetail.setPrice(rq.getPrice());
            orderDetail.setSoluong(rq.getSoluong());
            orderDetail.setSubTotal(rq.getPrice() * rq.getSoluong());
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail); 
            emailContent.append("<tr>")
                        .append("<td style=\"border: 1px solid #ddd; padding: 8px;\">").append(orderDetail.getName()).append("</td>")
                        .append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">").append(orderDetail.getSoluong()).append("</td>")
                        .append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">").append(orderDetail.getPrice()).append("</td>")
                        .append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">").append(orderDetail.getSubTotal()).append("</td>")
                        .append("</tr>");
        }
        emailContent.append("</table>")
                    .append("<p style=\"text-align: right; font-size: 16px; margin-top: 10px;\">Tổng tiền: <strong>").append(order.getTotalPrice()).append(" VND</strong></p>")
                    .append("</div>")
                    .append("<p style=\"text-align: center; margin-top: 20px;\">Vui lòng kiểm tra lại thông tin đơn hàng của bạn.</p>")
                    .append("</div>");

        try {
            emailUtil.sendOrderConfirmationEmail(request.getEmail(), order, emailContent.toString());
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send email, please try again");
        }
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getList() {
        return orderRepository.findAll(Sort.by("id").descending());
    }
    
    @Override
    public long getTotalOrders() {
        return orderRepository.count();
    }
    @Override
    public long calculateTotalRevenueByStatus() {
        return orderRepository.calculateTotalRevenueByStatus();
    }
//    @Override
//    public List<Order> getListOrdercharts(int number) {
//        // TODO Auto-generated method stub
//        List<Order> list = orderRepository.getListOrdercharts(number);
//        return list;
//    }
    @Override
    public List<Order> getListOrdercharts(int number) {
        List<Object[]> list = orderRepository.getListOrdercharts(number);
        List<Order> orders = new ArrayList<>();
        for (Object[] objArray : list) {
            Order order = new Order();
            order.setMonthYear((String) objArray[0]);
            order.setTotalRevenue((BigDecimal) objArray[1]);
            orders.add(order);
        }
        return orders;
    }
    
    @Override
    public List<Orderstatus> getListstatus() {
        return orderstatusRepository.findAll(Sort.by("id").descending());
    }
    @Override
    public List<Order> getOrderByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + username));

        List<Order> orders = orderRepository.getOrderByUser(user.getId());
        return orders;  
    }

//    @Override
//    public ResponseEntity<?> checkOrder(String orderCode) {
//        Order order = orderRepository.findByOrderCode(orderCode).orElseThrow();
////        order.setOrderState(1);
//        orderRepository.save(order);
//        return ResponseEntity.ok(new HashMap<>());
//    }
    @Override
    public Order checkOrder(String orderCode){
    	Order order = orderRepository.findByOrderCode(orderCode).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        return order;
    }
    @Override
    public ResponseEntity<?> removeOrder(String code) {
        return ResponseEntity.ok(new HashMap<>());
    }
    @Override
    public Order updateOrder(long id, CreateOrderRequest request) {
    	
    	// TODO Auto-generated method stub
    	Order order= orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
    	
    	Orderstatus orderstatus = orderstatusRepository.findById(request.getStatus()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getStatus()));
    	order.setOrderstatus(orderstatus);  
    	orderRepository.save(order);

        return order;
    }
    @Override
    public Order updateOrderstatus(long id, CreateOrderRequest request) {
    	
    	// TODO Auto-generated method stub
    	Order order= orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
    	Orderstatus orderstatus = orderstatusRepository.findById(4L).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getStatus()));
    	order.setOrderstatus(orderstatus);
    	orderRepository.save(order);

        return order;
    }
    @Override
    public Long getMaxOrderId() {
        return orderRepository.getMaxOrderId();
    }
    @Override
    public List<Order> getListOrder(long id) {
	 List<Order> list = orderRepository.getListOrder(id);
        return list;
    }
}
