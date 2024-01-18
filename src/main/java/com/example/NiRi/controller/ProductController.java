package com.example.NiRi.controller;

import com.example.NiRi.modules.Products;
import com.example.NiRi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Products addProduct(@RequestBody Products product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/all")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getById")
    public Products getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public Products updateProduct(@PathVariable Long id, @RequestBody Products updatedProduct) {
        return productService.updateProductDetails(id, updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
