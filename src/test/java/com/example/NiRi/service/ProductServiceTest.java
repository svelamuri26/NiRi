package com.example.NiRi.service;

import com.example.NiRi.modules.Products;
import com.example.NiRi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;



class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void saveProduct() {
        Products productToSave = new Products();
        when(productRepository.save(any(Products.class))).thenReturn(productToSave);

        Products savedProduct = productService.saveProduct(productToSave);

        assertNotNull(savedProduct);

    }

    @Test
    void getAllProducts() {
        List<Products> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        List<Products> result = productService.getAllProducts();

        assertEquals(productList, result);
    }

    @Test
    void getProductById() {
        Long productId = 1L;
        Products product = new Products();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Products result = productService.getProductById(productId);

        assertEquals(product, result);
    }

    @Test
    void updateProductDetails() {
        Long productId = 1L;
        Products existingProduct = new Products();
        Products updatedProduct = new Products();
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Products.class))).thenReturn(updatedProduct);

        Products result = productService.updateProductDetails(productId, updatedProduct);

        assertEquals(updatedProduct, result);
    }

    @Test
    void getProductsByPriceRange() {
        float minPrice = 10.0f;
        float maxPrice = 20.0f;
        List<Products> productList = new ArrayList<>();
        when(productRepository.findByPriceRange(minPrice, maxPrice)).thenReturn(productList);

        List<Products> result = productService.getProductsByPriceRange(minPrice, maxPrice);

        assertEquals(productList, result);
    }

    @Test
    void getProductByName() {
        String productName = "SampleProduct";
        Products product = new Products();
        List<Products> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findByName(productName)).thenReturn(productList);

        Products result = productService.getProductByName(productName);

        assertEquals(product, result);
    }

    @Test
    void deleteProduct() {
        Long productId = 1L;

        assertDoesNotThrow(() -> productService.deleteProduct(productId));
        verify(productRepository, times(1)).deleteById(productId);
    }
}
