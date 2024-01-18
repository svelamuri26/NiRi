package com.example.NiRi.controller;

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

    @GetMapping("/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long userId) {
        return cartItemService.getCartItemsByUserId(userId);
    }

    @PostMapping("/{userId}/add")
    public void addToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        cartItemService.addToCart(userId, productId, quantity);
    }

    @PostMapping("/remove/{cartItemId}")
    public void removeFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
    }
}