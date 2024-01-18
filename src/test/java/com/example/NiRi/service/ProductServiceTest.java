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

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void saveProduct() {

        Products productToSave = new Products(null, "Test Product", 10.0f, "Description", 5);
        Products savedProduct = new Products(1L, "Test Product", 10.0f, "Description", 5);

        when(productRepository.save(productToSave)).thenReturn(savedProduct);

        Products result = productService.saveProduct(productToSave);

        assertNotNull(result);
        assertEquals(savedProduct.getId(), result.getId());
        assertEquals(savedProduct.getName(), result.getName());
        assertEquals(savedProduct.getPrice(), result.getPrice());
        assertEquals(savedProduct.getDescription(), result.getDescription());
        assertEquals(savedProduct.getStock(), result.getStock());

        verify(productRepository, times(1)).save(productToSave);
    }

    @Test
    void getAllProducts() {

        List<Products> productList = Arrays.asList(
                new Products(1L, "Product 1", 20.0f, "Description 1", 10),
                new Products(2L, "Product 2", 30.0f, "Description 2", 15)
        );

        when(productRepository.findAll()).thenReturn(productList);

        List<Products> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(productList.size(), result.size());
        assertEquals(productList.get(0).getId(), result.get(0).getId());
        assertEquals(productList.get(1).getName(), result.get(1).getName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {

        Long productId = 1L;
        Products product = new Products(productId, "Test Product", 25.0f, "Description", 8);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Products result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals(product.getName(), result.getName());


        verify(productRepository, times(1)).findById(productId);
    }
    @Test
    void updateProductDetails() {

        Long productId = 1L;
        Products existingProduct = new Products(productId, "Existing Product", 30.0f, "Old Description", 12);
        Products updatedProduct = new Products(productId, "Updated Product", 40.0f, "New Description", 20);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        Products result = productService.updateProductDetails(productId, updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());
        assertEquals(updatedProduct.getDescription(), result.getDescription());
        assertEquals(updatedProduct.getStock(), result.getStock());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
    }
    @Test
    void deleteProduct() {

        Long productId = 1L;
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }
}
