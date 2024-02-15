package com.example.NiRi.modules;

public class OrderResponse {

    private Order order;
    private double totalPrice;

    public OrderResponse(Order order, double totalPrice) {
        this.order = order;
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}