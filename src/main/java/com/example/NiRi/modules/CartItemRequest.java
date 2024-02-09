package com.example.NiRi.modules;

import com.example.NiRi.modules.CartItemPayload;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemRequest extends CartItemPayload {

    private Long productId;
    private int quantity;

    @JsonCreator
    public static CartItemRequest create(
            @JsonProperty("productId") Long productId,
            @JsonProperty("quantity") int quantity) {
        CartItemRequest cartItemRequest = new CartItemRequest(productId, quantity);
        return cartItemRequest;
    }

    public CartItemRequest(Long productId, int quantity) {
        this.productId = productId;
        this.setQuantity(quantity);
    }
    public Long getProductId() {
        return productId;
    }
}
