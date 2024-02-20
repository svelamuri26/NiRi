package com.example.NiRi.modules;

import java.util.List;

public class UpdateCartItemRequest {

    private Long userId;

    private List<CartItemRequest> cartItems;

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
}

