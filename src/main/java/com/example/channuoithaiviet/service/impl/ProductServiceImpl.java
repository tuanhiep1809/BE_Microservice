package com.example.channuoithaiviet.service.impl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.channuoithaiviet.entity.Category;
import com.example.channuoithaiviet.entity.Image;
import com.example.channuoithaiviet.entity.Product;
import com.example.channuoithaiviet.entity.Productcolor;
import com.example.channuoithaiviet.entity.Producttype;
import com.example.channuoithaiviet.entity.Productsize;
import com.example.channuoithaiviet.entity.User;
import com.example.channuoithaiviet.exception.NotFoundException;
import com.example.channuoithaiviet.model.request.CreateProductRequest;
import com.example.channuoithaiviet.repository.CategoryRepository;
import com.example.channuoithaiviet.repository.ImageRepository;
import com.example.channuoithaiviet.repository.ProductRepository;
import com.example.channuoithaiviet.repository.ProductcolorRepository;
import com.example.channuoithaiviet.repository.ProducttypeRepository;
import com.example.channuoithaiviet.repository.ProductsizeRepository;
import com.example.channuoithaiviet.repository.UserRepository;
import com.example.channuoithaiviet.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductcolorRepository productcolorRepository;
    @Autowired
    private ProductsizeRepository productsizeRepository;
    @Autowired
    private ProducttypeRepository producttypeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Product> getList() {
        // TODO Auto-generated method stub
        return productRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Product getProduct(long id) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));

        return product;
    }
    

    @Override
    public Product createProduct(CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setMota(request.getMota());
        product.setPrice(request.getPrice());     
        product.setPricesale(0);   
        product.setCreateAt(new Timestamp(System.currentTimeMillis()));
        product.setQuantity(request.getQuantity());        
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);
        Set<Image> images = new HashSet<>();
        for(long imageId: request.getImageIds()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        
        //
        Set<Productcolor> productcolors = new HashSet<>();
        for(Long productcolorId : request.getProductcolors()){
        	Productcolor productcolor = productcolorRepository.findById(productcolorId).orElseThrow(() -> new NotFoundException("Not Found color"));
        	productcolors.add(productcolor);
        }
        product.setProductcolors(productcolors);
        
        Set<Productsize> productsizes = new HashSet<>();
        for(Long productsizeId : request.getProductsizes()){
        	Productsize productsize = productsizeRepository.findById(productsizeId).orElseThrow(() -> new NotFoundException("Not Found size"));
        	productsizes.add(productsize);
        }
        product.setProductsizes(productsizes);
        
        Set<Producttype> producttypes = new HashSet<>();
        for(Long producttypeId : request.getProducttypes()){
        	Producttype producttype = producttypeRepository.findById(producttypeId).orElseThrow(() -> new NotFoundException("Not Found type"));
        	producttypes.add(producttype);
        }
        product.setProducttypes(producttypes);
        //
        
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
        product.setUser(user);
        productRepository.save(product);
        return product;
    }
    @Override
    public Product updateProductgiamgia(long id, CreateProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));

        long originalPrice = product.getPrice(); // Lấy giá ban đầu của sản phẩm
        long discountPercentage = request.getPricesale(); // Lấy phần trăm giảm giá từ giá sale nhập vào
        product.setPricesale(discountPercentage); // Thiết lập giá sale cho sản phẩm

        long discountedPrice = originalPrice - (originalPrice * discountPercentage / 100); // Tính toán giá mới sau giảm giá
        product.setPrice(discountedPrice); // Thiết lập giá mới cho sản phẩm

        productRepository.save(product); // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu

        return product; // Trả về sản phẩm đã cập nhật
    }


    @Override
    public Product updateProduct(long id, CreateProductRequest request) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setMota(request.getMota());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + request.getCategoryId()));
        product.setCategory(category);

        Set<Image> images = new HashSet<>();
        for(long imageId: request.getImageIds()){
            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
            images.add(image);
        }
        product.setImages(images);
        
        //
        Set<Productcolor> productcolors = new HashSet<>();
        for(Long productcolorId : request.getProductcolors()){
        	Productcolor productcolor = productcolorRepository.findById(productcolorId).orElseThrow(() -> new NotFoundException("Not Found color"));
        	productcolors.add(productcolor);
        }
        product.setProductcolors(productcolors);
        
        Set<Productsize> productsizes = new HashSet<>();
        for(Long productsizeId : request.getProductsizes()){
        	Productsize productsize = productsizeRepository.findById(productsizeId).orElseThrow(() -> new NotFoundException("Not Found size"));
        	productsizes.add(productsize);
        }
        product.setProductsizes(productsizes);
        
        Set<Producttype> producttypes = new HashSet<>();
        for(Long producttypeId : request.getProducttypes()){
        	Producttype producttype = producttypeRepository.findById(producttypeId).orElseThrow(() -> new NotFoundException("Not Found type"));
        	producttypes.add(producttype);
        }
        product.setProducttypes(producttypes);
        //
        productRepository.save(product);

        return product;
    }

    @Override
    public void deleteProduct(long id) {
        // TODO Auto-generated method stub
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));
        product.getImages().remove(this);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getListNewst(int number) {
        // TODO Auto-generated method stub
        List<Product> list = productRepository.getListNewest(number);
        return list;
    }
    
    @Override
    public long getTotalProducts() {
        return productRepository.count();
    }
    @Override
    public long getTotalQuantitybuy() {
        return productRepository.sumQuantitybuy();
    }
//    @Override
//    public List<Product> getTopSellingProductsLast30Days() {
//        return productRepository.findTopSellingProductsLast30Days();
//    }
    @Override
    public List<Product> getListByPrice() {
        // TODO Auto-generated method stub
        return productRepository.getListByPrice();
    }

    @Override
    public List<Product> findRelatedProduct(long id){
        List<Product> list = productRepository.findRelatedProduct(id);
        return list;

    }

    @Override
    public List<Product> getListProductByCategory(long id){
        List<Product> list =productRepository.getListProductByCategory(id);
        return list;
    }
    @Override
    public List<Product> getListProductByUser(long id){
        List<Product> list =productRepository.getListProductByUser(id);
        return list;
    }

    @Override
    public List<Product> getListByPriceRange(long id,int min, int max){
        List<Product> list =productRepository.getListProductByPriceRange(id, min, max);
        return list;
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        // TODO Auto-generated method stub
        List<Product> list = productRepository.searchProduct(keyword);
        return list;
    }
    
    @Override
    public void updateProductQuantity(long id, int quantity,int quantitybuy) {
        Product product= productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Product With Id: " + id));       
        product.setQuantity(quantity);
        product.setQuantitybuy(quantitybuy);
        productRepository.save(product);
    }

   


    
}
