package com.example.NiRi.modules;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
public class Order {


    @Id
    @Column(insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;


    @Column(nullable = false)
    private int orderId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private LocalDateTime orderDate;


    public Order(User user, List<CartItem> cartItems, double totalPrice, LocalDateTime orderDate,int orderId) {
        this.user = user;
        this.cartItems = (cartItems != null) ? cartItems : new ArrayList<>();
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderId = orderId;
    }

    public Order() {

    }


    public int getId() {
        return orderId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser() {

    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}