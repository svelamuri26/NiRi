package com.example.NiRi.controller;

import com.example.NiRi.modules.*;
import com.example.NiRi.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long userId) {
        return cartItemService.getCartItemsByUserId(userId);
    }

    @PutMapping("/{userId}/add")
    public ResponseEntity<String> addToCart(@PathVariable Long userId, @RequestBody CartItemPayload cartItemPayload) {
        try {
            CartItemRequest cartItemRequest = new CartItemRequest(cartItemPayload.getProductId(), cartItemPayload.getQuantity());
            cartItemService.addToCart(userId, cartItemRequest);
            return ResponseEntity.ok("Item added to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding item to cart.");
        }
    }

    @PostMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId) {
        try {
            cartItemService.removeFromCart(cartItemId);
            return ResponseEntity.ok("Item removed from cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing item from cart.");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCartItem(@RequestBody UpdateCartItemRequest updateCartItemsRequest) {
        try {
            cartItemService.updateCartItem(updateCartItemsRequest);
            return ResponseEntity.ok("Cart items updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating cart items.");
        }
    }
}
