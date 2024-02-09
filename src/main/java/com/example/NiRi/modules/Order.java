package com.example.NiRi.modules;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    private double totalPrice;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    public Order() {

    }

    public Order(User user, List<CartItem> cartItems, double totalPrice, LocalDateTime orderDate) {
        this.user = user;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser() {

    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
