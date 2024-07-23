package com.example.channuoithaiviet.util;

import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.channuoithaiviet.entity.Order;
import com.example.channuoithaiviet.entity.OrderDetail;
import com.example.channuoithaiviet.repository.OrderDetailRepository;


@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
    private OrderDetailRepository orderDetailRepository;
	
	public void sendOtpEmail(String email, String verificationCode) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String emailContent = "<div>"
        		  + "<p>Mã OTP của bạn là : " + verificationCode + "</p>"
        		  + "<p>Gmail của bạn là: " + email + "</p>"
                + "<a href=\"http://localhost:8080/api/user/verify-account?email=" + email + "&otp=" + verificationCode + "\" target=\"_blank\">Click tại đây !</a>"
//                + "<a href=\"http://tazashop.xyz:8080/api/user/verify-account?email=" + email + "&otp=" + verificationCode + "\" target=\"_blank\">Click tại đây !</a>"
                + "</div>";
        
       
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Xác thực OTP để đăng nhập !");
        mimeMessageHelper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);
    }
//	 public void sendOrderConfirmationEmail(String email, Order order,OrderDetail orderDetail) throws MessagingException {
//	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//	        // Tạo nội dung email chứa thông tin về đơn hàng
//	        StringBuilder emailContent = new StringBuilder();
//	        emailContent.append("<div style=\"font-family: Arial, sans-serif; padding: 20px;\">")
//	                    .append("<h2 style=\"text-align: center;\">Xác nhận đơn hàng</h2>")
//	                    .append("<p style=\"font-size: 16px;\">Xin chào ").append(order.getLastname()).append(",</p>")
//	                    .append("<p style=\"font-size: 14px;\">Cảm ơn bạn đã đặt hàng từ chúng tôi. Đơn hàng của bạn có mã: <strong>")
//	                    .append(order.getOrderCode()).append("</strong></p>")
//	                    .append("<div style=\"border: 1px solid #ccc; padding: 10px; margin-top: 10px;\">")
//	                    .append("<h3 style=\"text-align: center; font-size: 16px;\">Chi tiết đơn hàng</h3>")
//	                    .append("<table style=\"width: 100%; border-collapse: collapse; font-size: 14px;\">")
//	                    .append("<tr>")
//	                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Tên sản phẩm</th>")
//	                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Số lượng</th>")
//	                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Giá</th>")
//	                    .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Tổng</th>")
//	                    .append("</tr>");
//	        
//	        // Sử dụng orderDetail truyền vào từ tham số để hiển thị thông tin chi tiết đơn hàng
//	        emailContent.append("<tr>")
//	                    .append("<td style=\"border: 1px solid #ddd; padding: 8px;\">").append(orderDetail.getName()).append("</td>")
//	                    .append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">").append(orderDetail.getSoluong()).append("</td>")
//	                    .append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align: right;\">").append(orderDetail.getPrice()).append("</td>")
//	                    .append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align: right;\">").append(orderDetail.getSubTotal()).append("</td>")
//	                    .append("</tr>");
//	        emailContent.append("</table>")
//	                    .append("<p style=\"text-align: right; font-size: 16px; margin-top: 10px;\">Tổng tiền: <strong>").append(order.getTotalPrice()).append(" VND</strong></p>")
//	                    .append("</div>")
//	                    .append("<p style=\"text-align: center; margin-top: 20px;\">Vui lòng kiểm tra lại thông tin đơn hàng của bạn.</p>")
//	                    .append("</div>");
//
//	        mimeMessageHelper.setTo(email);
//	        mimeMessageHelper.setSubject("Xác nhận đơn hàng #" + order.getOrderCode());
//	        mimeMessageHelper.setText(emailContent.toString(), true);
//	        javaMailSender.send(mimeMessage);
//	    }
	public void sendOrderConfirmationEmail(String email, Order order, String emailContent) throws MessagingException {
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // Thiết lập mã hóa UTF-8

	    mimeMessageHelper.setTo(email);
	    mimeMessageHelper.setSubject("Xác nhận đơn hàng #" + order.getOrderCode());
	    mimeMessageHelper.setText(emailContent, true);
	    
	    javaMailSender.send(mimeMessage);
	}

}