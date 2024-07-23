package com.example.channuoithaiviet.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.channuoithaiviet.entity.OrderDetail;
import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.excel.ProductExcelExport;
import com.example.channuoithaiviet.model.request.CreateProductRequest;
import com.example.channuoithaiviet.model.response.MessageResponse;
import com.example.channuoithaiviet.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    @Operation(summary="Lấy ra danh sách sản phẩm")
    public ResponseEntity<List<Product>> getList(){
        List<Product> list = productService.getList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/total")
    @Operation(summary = "Lấy tổng số lượng sản phẩm")
    public ResponseEntity<Long> getTotalProducts() {
        long totalProducts = productService.getTotalProducts();
        return ResponseEntity.ok(totalProducts);
    }

    
    @GetMapping("/total-quantity")
    @Operation(summary = "Lấy tổng số lượng bán của tất cả sản phẩm")
    public ResponseEntity<Long> getTotalQuantity() {
        long totalQuantity = productService.getTotalQuantitybuy();
        return ResponseEntity.ok(totalQuantity);
    }
//    @GetMapping("/top-selling")
//    @Operation(summary = "Lấy ra danh sách các sản phẩm bán chạy trong 30 ngày qua")
//    public ResponseEntity<List<Product>> getTopSellingProductsLast30Days() {
//        List<Product> topSellingProducts = productService.getTopSellingProductsLast30Days();
//        return ResponseEntity.ok(topSellingProducts);
//    }
    
    @GetMapping("/newest/{number}")
    @Operation(summary="Lấy ra danh sách sản phẩm mới nhất giới hạn số lượng = number")
    public ResponseEntity<List<Product>> getListNewst(@PathVariable int number){
        List<Product> list =productService.getListNewst(number);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/price")
    @Operation(summary="Lấy ra danh sách 8 sản phẩm có giá từ thấp nhất đến cao")
    public ResponseEntity<List<Product>> getListByPrice(){
        List<Product> list = productService.getListByPrice();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/related/{id}")
    @Operation(summary="Lấy ra ngẫu nhiên 4 sản phẩm bằng category_id")
    public ResponseEntity<List<Product>> getListRelatedProduct(@PathVariable long id){
        List<Product> list = productService.findRelatedProduct(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/category/{id}")
    @Operation(summary="Lấy ra danh sách sản phẩm bằng id của danh mục")
    public ResponseEntity<List<Product>> getListProductByCategory(@PathVariable long id){
        List<Product> list =  productService.getListProductByCategory(id);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/pagesanpham/{id}")
    @Operation(summary="Lấy ra danh sách sản phẩm theo id người dùng")
    public ResponseEntity<List<Product>> getListProductByUser(@PathVariable long id){
        List<Product> list =  productService.getListProductByUser(id);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/range")
    @Operation(summary="Lấy ra danh sách sản phẩm ở các mức giá từ min đến max")
    public ResponseEntity<List<Product>> getListProductByPriceRange(@RequestParam("id") long id,@RequestParam("min") int min, @RequestParam("max") int max){
        List<Product> list = productService.getListByPriceRange(id, min, max);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary="Lấy sản phẩm bằng id")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        Product product = productService.getProduct(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    @Operation(summary="Tìm kiếm sản phẩm bằng keyword")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam("keyword") String keyword){
        List<Product> list = productService.searchProduct(keyword);
        return ResponseEntity.ok(list);
    }
    
    
    @PostMapping("/create")
    @Operation(summary="Tạo mới sản phẩm")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request){
        Product product = productService.createProduct(request);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    @Operation(summary="Tìm sản phẩm bằng id và cập nhật sản phẩm đó")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody CreateProductRequest request){
        Product product = productService.updateProduct(id, request);

        return ResponseEntity.ok(product);
    }
    @PutMapping("/updategiamgia/{id}")
    @Operation(summary="Tìm sản phẩm bằng id và cập nhật giảm giá sản phẩm đó")
    public ResponseEntity<Product> updateProductGiamGia(@PathVariable long id, @RequestBody CreateProductRequest request) {
        Product product = productService.updateProductgiamgia(id, request);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary="Xóa sản phẩm bằng id")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);

        return ResponseEntity.ok(new MessageResponse("Product is d  elete"));
    }

    @PutMapping("/{id}/quantity")
    @Operation(summary="Trừ số lượng sản phẩm theo id sản phẩm")
    public ResponseEntity<?> updateProductQuantity(@PathVariable Long id, @RequestParam int quantity,@RequestParam int quantitybuy) {
        productService.updateProductQuantity(id, quantity,quantitybuy);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/productexcel")
	@Operation(summary="Xuất excel sản phẩm")
    public ModelAndView exportToExcel(HttpServletResponse response) {      
        List<Product> products = productService.getList();
        ModelAndView modelAndView = new ModelAndView(new ProductExcelExport(), "products", products);
        return modelAndView;
    }

}
