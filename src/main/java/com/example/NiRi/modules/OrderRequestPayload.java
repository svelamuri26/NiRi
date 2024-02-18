package com.example.NiRi.modules;

public class OrderRequestPayload {

    private Long userId;
    private int cartItemId;
    private int orderId;
    //private float totalPrice;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //public List<Long> getCartItemIds() {return cartItemIds;}

   // public void setCartItemIds(List<Long> cartItemIds) {this.cartItemIds = cartItemIds;}

    //public float getTotalPrice() {return totalPrice;
    //}

    //public void setTotalPrice(float totalPrice) {this.totalPrice = totalPrice;}

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}