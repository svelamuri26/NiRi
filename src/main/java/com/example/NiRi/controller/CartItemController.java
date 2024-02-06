package com.example.NiRi.controller;

import com.example.NiRi.CartItemRequest;
import com.example.NiRi.modules.CartItem;
import com.example.NiRi.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{user_id}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long user_id) {
        return cartItemService.getCartItemsByUserId(user_id);
    }

    @PostMapping("/{userId}/add")
    public void addToCart(@PathVariable Long userId, @RequestBody CartItemRequest cartItemRequest) {
        cartItemService.addToCart(userId, cartItemRequest.getProductId(), cartItemRequest.getQuantity());
    }

    @PostMapping("/remove/{cartItemId}")
    public void removeFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
    }

}