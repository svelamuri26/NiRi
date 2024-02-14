package com.example.NiRi.service;

import com.example.NiRi.modules.*;
import com.example.NiRi.repository.CartItemRepository;
import com.example.NiRi.repository.ProductRepository;
import com.example.NiRi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@Transactional
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    @Autowired
    private final UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);

    @Autowired
    public CartItemService(
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;

    }


    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void removeFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found with ID: " + cartItemId));

        cartItemRepository.delete(cartItem);
    }

    public List<CartItem> getCartItemsByIds(List<Long> cartItemIds) {
        return cartItemRepository.findAllById(cartItemIds);
    }
    @Transactional
    public void addToCart(Long userId, CartItemRequest cartItemRequest) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

            Products product = productRepository.findById(cartItemRequest.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + cartItemRequest.getProductId()));

            CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

            if (existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(cartItemRequest.getQuantity());
                cartItem.setStatus("Added");
                Order orderObject = null;
                cartItem.setOrder(orderObject);

                cartItemRepository.save(cartItem);
            }
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("User or product not found", e);
        } catch (Exception e) {
            throw new RuntimeException("Error adding to cart", e);
        }
    }

    public void updateCartItem(UpdateCartItemRequest updateCartItemsRequest) {
        for (CartItemPayload cartItemPayload : updateCartItemsRequest.getCartItems()) {
            CartItem cartItem = cartItemRepository.findById(cartItemPayload.getCartItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Cart item not found with ID: " + cartItemPayload.getCartItemId()));
            cartItem.setQuantity(cartItemPayload.getQuantity());
        }
    }
}

