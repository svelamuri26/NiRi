package com.example.NiRi.modules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CartItemPayload {

    private Long userId;
    private List<CartItemRequest> cartItems;
    private Long cartItemId;
    private Long productId;  // Add this field

    private int quantity; // Make it accessible in the subclass

    public CartItemPayload() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemRequest> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemRequest> cartItems) {
        this.cartItems = cartItems;
    }

    // Provide a setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
