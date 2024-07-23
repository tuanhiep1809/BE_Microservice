package com.example.channuoithaiviet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.channuoithaiviet.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    
    @Query("Select c from Category c where c.enable = true")
    List<Category> findALLByEnabled();
    @Query("SELECT p.category.id, COUNT(*) AS product_count FROM Product p GROUP BY p.category.id")
    List<Object[]> countProductsByCategory();
}
