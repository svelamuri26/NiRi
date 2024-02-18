package com.example.NiRi.controller;

import com.example.NiRi.modules.Order;
import com.example.NiRi.modules.Products;
import com.example.NiRi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;

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

        List<Products> products= productService.getAllProducts();
        System.out.println("products All");
        System.out.println(products);
        return products;
    }


    /*
    @GetMapping("/all")
    public ResponseEntity<List<Products>> getAllProducts() {
        try {
            List<Products> products = productService.getAllProducts();
            System.out.println("products::***********");
            System.out.println(products);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */


    @GetMapping("/getById")
    public Products getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public Products updateProduct(@PathVariable Long id, @RequestBody Products updatedProduct) {
        return productService.updateProductDetails(id, updatedProduct);
    }
    @GetMapping("/getByName")
    public Products getProductByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }
    @GetMapping("/getByPriceRange")
    public List<Products> getProductsByPriceRange(@RequestParam float minPrice, @RequestParam float maxPrice) {
        return productService.getProductsByPriceRange(minPrice, maxPrice);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
