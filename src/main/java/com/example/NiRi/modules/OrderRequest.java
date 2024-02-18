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

        //super();
        this.userId = userId;
        this.setUserId(userId);
        this.orderId = orderId;
        this.setOrderId(orderId);
        this.cartItemId = cartItemId;
        System.out.println("orderId88::***********");
        System.out.println(cartItemId);
        this.setCartItemId(cartItemId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public  int getOrderId() {
        System.out.println("orderId***********");
        System.out.println(orderId);
        return orderId;
    }

    public void setOrderId(int orderId) {
        System.out.println("66order***********");
        System.out.println(orderId);
        this.orderId = orderId;
    }

    public  int getCartItemId() {
        System.out.println("orderId***********");
        System.out.println(cartItemId);
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        System.out.println("order66***********");
        System.out.println(cartItemId);
        this.cartItemId = cartItemId;
    }
}
