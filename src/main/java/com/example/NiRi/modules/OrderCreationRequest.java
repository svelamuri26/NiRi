package com.example.NiRi.modules;

import java.util.List;

public class OrderCreationRequest {

    private Long userId;
    private List<Long> cartItemIds;
    private float totalPrice;
    private String orderDate;
    private int orderId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getCartItemIds() {return cartItemIds;}

   public void setCartItemIds(List<Long> cartItemIds) {this.cartItemIds = cartItemIds;}

    public float getTotalPrice() {return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {this.totalPrice = totalPrice;}

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void orderId(Long userId) {
        this.orderId = orderId;
    }
}