package com.example.NiRi.modules;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int stock;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CartItem> cartItems = new ArrayList<>();

    public Products() {
    }

    public Products(Long id, String name, float price, String description, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    public Products(String product1, double v) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems;
    }

    // Add this method with a different name to avoid ambiguity
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setProduct(this);
    }
}
