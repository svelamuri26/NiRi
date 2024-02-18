package com.example.NiRi.modules;

import java.util.List;

public class CartItemPayload {

    private Long userId;
    private List<CartItemRequest> cartItems;
    private int cartItemId;
    private Long productId;  // Add this field
    private int orderId;
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

    public List<CartItemRequest> getCartItems(int cartItemId) {
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

    public int getCartItemId() {
        System.out.println("orderId1010::***********");
        System.out.println(cartItemId);
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        System.out.println("orderId44::***********");
        System.out.println(cartItemId);
        this.cartItemId = cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getOrderId() {
        return orderId;
    }
}
