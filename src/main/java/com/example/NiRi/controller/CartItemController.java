package com.example.NiRi.controller;

import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.CartItemPayload;
import com.example.NiRi.modules.CartItemRequest;
import com.example.NiRi.modules.UpdateCartItemRequest;
import com.example.NiRi.service.CartItemService;
import com.example.NiRi.modules.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItemsByUserId(@PathVariable Long userId) {
        List<CartItem> cartItems = (List<CartItem>) cartItemService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

   @PostMapping("/{userId}/add")
    public ResponseEntity<ApiResponse> addCartItem(@PathVariable Long userId, @RequestBody CartItemPayload cartItemPayload) {
        try {
            System.out.println(cartItemPayload.getCartItemId());
            CartItemRequest cartItemRequest = new CartItemRequest(cartItemPayload.getProductId(), cartItemPayload.getQuantity(),cartItemPayload.getOrderId(),cartItemPayload.getCartItemId());
            System.out.println(cartItemRequest.getCartItemId());
            cartItemService.addToCart(userId, cartItemRequest);
            return ResponseEntity.ok(new ApiResponse(true, "Item added to cart successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Failed to add item to cart."));
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<ApiResponse> removeFromCart(@RequestBody  CartItemPayload cartItemPayload) {
        try {
            cartItemService.removeFromCart(cartItemPayload.getCartItemId(),cartItemPayload.getProductId());
            ApiResponse response = new ApiResponse(true, "Item removed from cart successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Error removing item from cart.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody CartItemPayload cartItemPayload) {
        try {
            cartItemService.updateCartItem(cartItemPayload);
            return ResponseEntity.ok(new ApiResponse(true, "Cart items updated successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error updating cart items."));
        }
    }

}