package com.example.NiRi.controller;

import com.example.NiRi.modules.Products;
import com.example.NiRi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void addProduct() {
        Products productToAdd = new Products(); // Create a sample product for testing
        when(productService.saveProduct(productToAdd)).thenReturn(productToAdd);

        Products addedProduct = productController.addProduct(productToAdd);

        assertEquals(productToAdd, addedProduct);
        verify(productService, times(1)).saveProduct(productToAdd);
    }

    @Test
    void getAllProducts() {
        List<Products> expectedProducts = Arrays.asList(new Products(), new Products());
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        List<Products> actualProducts = productController.getAllProducts();

        assertEquals(expectedProducts, actualProducts);
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProductById() {
        long productId = 1L;
        Products expectedProduct = new Products();
        when(productService.getProductById(productId)).thenReturn(expectedProduct);

        Products actualProduct = productController.getProductById(productId);

        assertEquals(expectedProduct, actualProduct);
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void updateProduct() {
        long productId = 1L;
        Products updatedProduct = new Products();
        when(productService.updateProductDetails(productId, updatedProduct)).thenReturn(updatedProduct);

        Products actualProduct = productController.updateProduct(productId, updatedProduct);

        assertEquals(updatedProduct, actualProduct);
        verify(productService, times(1)).updateProductDetails(productId, updatedProduct);
    }

    @Test
    void getProductByName() {
        String productName = "SampleProduct";
        Products expectedProduct = new Products();
        when(productService.getProductByName(productName)).thenReturn(expectedProduct);

        Products actualProduct = productController.getProductByName(productName);

        assertEquals(expectedProduct, actualProduct);
        verify(productService, times(1)).getProductByName(productName);
    }

    @Test
    void getProductsByPriceRange() {
        float minPrice = 10.0f;
        float maxPrice = 20.0f;
        List<Products> expectedProducts = Arrays.asList(new Products(), new Products());
        when(productService.getProductsByPriceRange(minPrice, maxPrice)).thenReturn(expectedProducts);

        List<Products> actualProducts = productController.getProductsByPriceRange(minPrice, maxPrice);

        assertEquals(expectedProducts, actualProducts);
        verify(productService, times(1)).getProductsByPriceRange(minPrice, maxPrice);
    }

    @Test
    void deleteProduct() {
        long productId = 1L;

        doNothing().when(productService).deleteProduct(productId);

        productController.deleteProduct(productId);

        verify(productService, times(1)).deleteProduct(productId);
    }
}
