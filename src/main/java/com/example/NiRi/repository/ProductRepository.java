package com.example.NiRi.repository;

import com.example.NiRi.modules.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {

    @Query("SELECT p FROM Products p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Products> findByPriceRange(float minPrice, float maxPrice);

    List<Products> findByStockGreaterThan(int stock);

    List<Products> findByStockEquals(int stock);

    List<Products> findByPriceLessThan(float price);

    List<Products> findByName(String name);
}
