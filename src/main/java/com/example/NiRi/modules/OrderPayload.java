package com.example.NiRi.modules;

import java.util.List;

public class OrderPayload {
    private Long userId;
    private double totalPrice;
    private List<CartItemPayload> cartItems;

    public OrderPayload(Long userId, double totalPrice, List<CartItemPayload> cartItems) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItemPayload> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemPayload> cartItems) {
        this.cartItems = cartItems;
    }
}
