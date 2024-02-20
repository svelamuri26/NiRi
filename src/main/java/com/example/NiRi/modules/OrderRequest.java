package com.example.NiRi.modules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderRequest{

    private Long userId;
    private int orderId;
    private int cartItemId;

    @JsonCreator
    public OrderRequest(
            @JsonProperty("userId") Long userId,
            @JsonProperty("cartItemId") int cartItemId,
            @JsonProperty("orderId")  int orderId
        ) {

        this.userId = userId;
        this.setUserId(userId);
        this.orderId = orderId;
        this.setOrderId(orderId);
        this.cartItemId = cartItemId;
        this.setCartItemId(cartItemId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public  int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public  int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }
}
