package com.example.channuoithaiviet.excel;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.example.channuoithaiviet.entity.Order;
import com.example.channuoithaiviet.entity.OrderDetail;
import com.example.channuoithaiviet.entity.Orderstatus;

import java.util.HashMap;

public class OrderExcelExport extends AbstractXlsxView {
	
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// Đặt tên cho file Excel được tạo ra
        response.setHeader("Content-Disposition", "attachment;filename=orders.xlsx");
     // Đọc dữ liệu từ Controller
        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) model.get("orders");
        List<OrderDetail> orderDetails = (List<OrderDetail>) model.get("orderDetails");
     // Tạo một sheet mới
        Sheet sheet = workbook.createSheet("Order");
        DecimalFormat currencyFormat = new DecimalFormat("#,### VND");
     // Tạo style cho header
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);        
     // Tạo header cho đơn hàng
        Row headerRow = sheet.createRow(0);      
        Row headerRow1 = sheet.createRow(1);        
     // tiêu đề
     // Tạo một font in đậm
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        // Tạo một style và thiết lập font in đậm
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(boldFont);
        // Tạo ô và thiết lập giá trị cho ô đó với style in đậm
        Cell headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Sàn Thương Mại Điện Tử TAZA");
        headerCell.setCellStyle(boldStyle);
        int rowNum = 1; // Bắt đầu từ hàng thứ hai vì hàng đầu tiên đã chứa tiêu đề        
            Row currentRow = sheet.createRow(rowNum++);
            
            Order id = orders.isEmpty() ? null : orders.get(0);
            if (id != null) {
            	currentRow.createCell(0).setCellValue("Mã Đơn Hàng : " + id.getId());
            }
            Order Address = orders.isEmpty() ? null : orders.get(0);
            if (Address != null) {
            	currentRow.createCell(1).setCellValue("Địa Chỉ: " + id.getAddress());
            }
          
            
            Row currentRow1 = sheet.createRow(rowNum++);
            
            Order Lastname = orders.isEmpty() ? null : orders.get(0);
            if (Lastname != null) {
            	currentRow1.createCell(0).setCellValue("Tên Khách Hàng: " + id.getLastname());
            }
            Order note = orders.isEmpty() ? null : orders.get(0);
            if (note != null ) {
//            	currentRow1.createCell(1).setCellValue("Ghi Chú: " + note.getNote());
            	currentRow1.createCell(1).setCellValue("Ghi Chú: " + (note != null ? note.getNote() : ""));

            }
            
            Row currentRow2 = sheet.createRow(rowNum++);            
            Order email = orders.isEmpty() ? null : orders.get(0);
            if (email != null) {
            	currentRow2.createCell(0).setCellValue("Email : " + email.getEmail());
            }
            Order phone = orders.isEmpty() ? null : orders.get(0);
            if (phone != null) {
            	currentRow2.createCell(1).setCellValue("Phone: " + phone.getPhone());
            }
            
            Row currentRow3 = sheet.createRow(rowNum++);            

            Order status = orders.isEmpty() ? null : orders.get(0);
            if (status != null) {
                String paymentMethod = "";
                Orderstatus orderStatusObject = status.getOrderstatus(); // Lấy đối tượng Orderstatus thông qua khóa ngoại
                if (orderStatusObject != null) {
                    long orderStatusId = orderStatusObject.getId(); // Lấy trường id từ đối tượng Orderstatus
                    if (orderStatusId == 0) {
                        paymentMethod = "Chờ Xác Nhận";
                    } else if (orderStatusId == 1) {
                        paymentMethod = "Chờ Lấy Hàng";
                    } else if (orderStatusId == 2) {
                        paymentMethod = "Chờ Giao Hàng";
                    } else if (orderStatusId == 3) {
                        paymentMethod = "Đã Giao";
                    } else if (orderStatusId == 4) {
                        paymentMethod = "Đã Hủy";
                    } else if (orderStatusId == 5) {
                        paymentMethod = "Trả Hàng";
                    } else if (orderStatusId == 6) {
                        paymentMethod = "Lỗi Hệ Thống";
                    } else {
                        paymentMethod = "Lỗi gì rồi";
                    }
                } else {
                    paymentMethod = "Lỗi gì rồi";
                }
                currentRow3.createCell(0).setCellValue("Phương thức thanh toán: " + paymentMethod);
            }  
            Order bank = orders.isEmpty() ? null : orders.get(0);
            if (bank != null) {
                String paymentMethod = "";
                if (bank.getBank() == 0) {
                    paymentMethod = "Thanh toán nhận hàng";
                } else if (bank.getBank() == 1) {
                    paymentMethod = "Thanh toán VNPay";
                }
                currentRow3.createCell(1).setCellValue("Phương thức thanh toán: " + paymentMethod);
            }
            
            Row currentRow4 = sheet.createRow(rowNum++);            
            Order giatien = orders.isEmpty() ? null : orders.get(0);
            if (giatien != null) {
            	currentRow4.createCell(1).setCellValue("Thành Tiền : " + currencyFormat.format(giatien.getTotalPrice()));
            }
            Order giamgia = orders.isEmpty() ? null : orders.get(0);
            if (giamgia != null) {
            	currentRow4.createCell(0).setCellValue("Giảm Giá: " + currencyFormat.format(giamgia.getSale()));
            }
         // Tạo một CellStyle mới cho tiêu đề và dữ liệu            
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            // Tạo một font in đậm cho tiêu đề            
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            // Tạo dòng tiêu đề và thiết lập các ô trong dòng này
            Row currentRow5 = sheet.createRow(rowNum++);
            Cell cell1 = currentRow5.createCell(0);
            cell1.setCellValue("Mã");
            cell1.setCellStyle(headerStyle);       
            // Thiết lập tiêu đề cho các ô còn lại
            Cell cell2 = currentRow5.createCell(1);
            cell2.setCellValue("Tên Sản Phẩm");
            cell2.setCellStyle(headerStyle);
            Cell cell3 = currentRow5.createCell(2);
            cell3.setCellValue("Số Lượng");
            cell3.setCellStyle(headerStyle);
            Cell cell4 = currentRow5.createCell(3);
            cell4.setCellValue("Giá");
            cell4.setCellStyle(headerStyle);
            Cell cell5 = currentRow5.createCell(4);
            cell5.setCellValue("Tổng Tiền");
            cell5.setCellStyle(headerStyle);
            // Tạo một CellStyle mới cho dữ liệu không in đậm
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            // Tiếp tục tạo các dòng dữ liệu và thiết lập các ô trong dòng này với CellStyle dataStyle
            for (OrderDetail orderDetail : orderDetails) {
                Row row = sheet.createRow(rowNum++);
                Cell cellId = row.createCell(0);
                cellId.setCellValue(orderDetail.getId());
                cellId.setCellStyle(dataStyle);
                Cell cellName = row.createCell(1);
                cellName.setCellValue(orderDetail.getName());
                cellName.setCellStyle(dataStyle);
                Cell cellQuantity = row.createCell(2);
                cellQuantity.setCellValue(orderDetail.getSoluong());
                cellQuantity.setCellStyle(dataStyle);
                Cell cellPrice = row.createCell(3);
                cellPrice.setCellValue(currencyFormat.format(orderDetail.getPrice()));
                cellPrice.setCellStyle(dataStyle);
                Cell cell = row.createCell(4);
                cell.setCellStyle(dataStyle);
            }
            Row footerRow1 = sheet.createRow(rowNum++);
            Cell cell0 = footerRow1.createCell(0);

            Cell cell11 = footerRow1.createCell(1);

            Cell cell12 = footerRow1.createCell(2);
            Cell cell13 = footerRow1.createCell(3);
            Cell cell14 = footerRow1.createCell(4);
            cell14.setCellValue(currencyFormat.format(giatien.getTotalPrice()));

            CellStyle style = workbook.createCellStyle();
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);

            cell0.setCellStyle(style);
            cell11.setCellStyle(style);
            cell13.setCellStyle(style);
            cell12.setCellStyle(style);
            cell14.setCellStyle(style);


         // Tạo dòng cuối cùng chứa ngày tháng năm và người xuất
            Row footerRow = sheet.createRow(rowNum++);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Ngày' dd 'Tháng' MM 'Năm' yyyy");
            String formattedDate = LocalDate.now().format(formatter);
            footerRow.createCell(3).setCellValue(formattedDate);
            Row footerRow2 = sheet.createRow(rowNum++);
            Cell footerCell = footerRow2.createCell(3);
            footerCell.setCellValue("Nhân Viên"); 
            footerCell.setCellStyle(boldStyle);

     // Tự động điều chỉnh kích thước các cột
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
   }
}