package com.example.NiRi.modules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CartItemRequest extends CartItemPayload {

    private Long productId;

    @JsonCreator
    public CartItemRequest(
            @JsonProperty("productId") Long productId,
            @JsonProperty("quantity") int quantity) {
        super();
        this.productId = productId;
        this.setQuantity(quantity);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
