package com.example.NiRi.controller;

import com.example.NiRi.modules.CartItemPayload;
import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.CartItemRequest;
import com.example.NiRi.modules.UpdateCartItemRequest;
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

    @GetMapping("/{user_id}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long user_id) {
        return cartItemService.getCartItemsByUserId(user_id);
    }

    @PostMapping("/{userId}/add")
    public void addToCart(@PathVariable Long userId, @RequestBody CartItemPayload cartItemPayload) {
        cartItemService.addToCart(userId, cartItemPayload);
    }

    @PostMapping("/remove/{cartItemId}")
    public void removeFromCart(@PathVariable Long cartItemId) {
        cartItemService.removeFromCart(cartItemId);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCartItems(@RequestBody UpdateCartItemRequest updateCartItemsRequest) {
        try {
            cartItemService.updateCartItem(updateCartItemsRequest);
            return ResponseEntity.ok("Cart items updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating cart items.");
        }
    }
}
