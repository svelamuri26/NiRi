package com.example.NiRi.modules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "order_id", nullable = false)
    @Column(name = "order_id",nullable = false)
    private int orderId;

    @Column(name = "cart_id")
    private int cartItemId;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false)
    private String status;

    public CartItem() {
        this.product = null;
        this.user = null;
        //this.orderId=0;
        this.quantity = 0;
        //this.status = null;
        this.cartItemId = 0;
    }

    public CartItem(Products product, int i) {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        if (this.product != null) {
            this.product.getCartItems().remove(this); // Remove from the old product's collection
        }
        this.product = product;
        if (product != null) {
            product.getCartItems().add(this); // Add to the new product's collection
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //public Order getOrder() {
       // return order;
    //}

    public int getOrderId() {
        return orderId;
    }

    //public void setOrder(Order order) {
        //this.order = order;
    //}

    public void setCartItemId(int cartItemId) {
        System.out.println("cart99***********");
        System.out.println(cartItemId);
    this.cartItemId = cartItemId;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    //public void setOrder(Order order) {
    //this.order = order;
    //}

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
