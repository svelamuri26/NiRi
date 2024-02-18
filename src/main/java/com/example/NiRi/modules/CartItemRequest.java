package com.example.NiRi.modules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CartItemRequest extends CartItemPayload {

    private Long productId;
    private int orderId;
    private int cartItemId;

    @JsonCreator
    public CartItemRequest(
            @JsonProperty("productId") Long productId,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("orderId")  int orderId,
            @JsonProperty("cartItemId")  int cartItemId) {
        super();
        this.productId = productId;
        this.setQuantity(quantity);
        this.orderId = orderId;
        this.setOrderId(orderId);
        this.cartItemId = cartItemId;
        System.out.println("cartId88::***********");
        System.out.println(cartItemId);
        this.setCartItemId(cartItemId);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public  int getOrderId() {
        System.out.println("orderId***********");
        System.out.println(orderId);
        return orderId;
    }

    public void setOrderId(int orderId) {
        System.out.println("orderId66order***********");
        System.out.println(orderId);
        this.orderId = orderId;
    }

    public  int getCartItemId() {
        System.out.println("cartId***********");
        System.out.println(cartItemId);
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        System.out.println("orderId66***********");
        System.out.println(cartItemId);
        this.cartItemId = cartItemId;
    }
}
