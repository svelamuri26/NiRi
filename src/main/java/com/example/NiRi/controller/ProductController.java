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
    @GetMapping("/search")
    public List<Products> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/filterByPriceRange")
    public List<Products> filterProductsByPriceRange(@RequestParam float minPrice, @RequestParam float maxPrice) {
        return productService.filterProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/filterByStock")
    public List<Products> filterProductsByStock(@RequestParam int stock) {
        return productService.filterProductsByStock(stock);
    }

    @GetMapping("/getAllSortedByName")
    public List<Products> getAllProductsSortedByName() {
        return productService.getAllProductsSortedByName();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
