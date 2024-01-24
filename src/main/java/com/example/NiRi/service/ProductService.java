package com.example.NiRi.service;

import com.example.NiRi.modules.Products;
import com.example.NiRi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService  {

    @Autowired
    private ProductRepository productRepository;

    public Products saveProduct(Products product) {
        return productRepository.save(product);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Products not found with id: " + id));
    }

    public Products updateProductDetails(Long id, Products updatedProduct) {
        Products existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Products not found with id: " + id));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setStock(updatedProduct.getStock());

        return productRepository.save(existingProduct);
    }

    public List<Products> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Products> filterProductsByPriceRange(float minPrice, float maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Products> filterProductsByStock(int stock) {
        return productRepository.findByStockGreaterThan(stock);
    }

    public List<Products> getAllProductsSortedByName() {
        return productRepository.findAll(Sort.by(Sort.Order.asc("name")));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
