package com.example.NiRi.repository;

import com.example.NiRi.modules.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(float minPrice, float maxPrice);

    List<Product> findByStockGreaterThan(int stock);

    List<Product> findByStockEquals(int stock);

    List<Product> findByPriceLessThan(float price);

}
