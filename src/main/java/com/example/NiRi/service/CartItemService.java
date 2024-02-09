package com.example.NiRi.service;

import com.example.NiRi.modules.*;
import com.example.NiRi.repository.CartItemRepository;
import com.example.NiRi.repository.OrderRepository;
import com.example.NiRi.repository.ProductRepository;
import com.example.NiRi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Order;
import com.example.NiRi.modules.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);

    @Autowired
    public CartItemService(
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            OrderRepository orderRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
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

    public Long createCartItem(CartItemRequest cartItemRequest) {
        try {
            User user = userRepository.findById(cartItemRequest.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + cartItemRequest.getUserId()));

            Products product = productRepository.findById(cartItemRequest.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + cartItemRequest.getProductId()));

            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());

            return cartItemRepository.save(cartItem).getId();
        } catch (EntityNotFoundException e) {
            logger.error("Error creating cart item - User or product not found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error creating cart item", e);
            throw new RuntimeException("Error creating cart item", e);
        }
    }
    @Transactional
    public void addToCart(Long userId, Long productId, int quantity, CartItemPayload cartItemPayload) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

            Products product = productRepository.findById(productId)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));

            CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

            if (existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                cartItemRepository.save(existingCartItem);
            } else {

                CartItem cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setStatus("SomeStatus");

                cartItemRepository.save(cartItem);
            }

        } catch (EntityNotFoundException e) {
            throw new RuntimeException("User or product not found", e);
        } catch (Exception e) {
            throw new RuntimeException("Error adding to cart", e);
        }
    }

    @Transactional
    public void updateCartItem(UpdateCartItemRequest updateCartItemsRequest) {
        for (CartItemPayload cartItemPayload : updateCartItemsRequest.getCartItems()) {
            CartItem cartItem = cartItemRepository.findById(cartItemPayload.getCartItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Cart item not found with ID: " + cartItemPayload.getCartItemId()));
            cartItem.setQuantity(cartItemPayload.getQuantity());
            cartItemRepository.save(cartItem);
        }
    }

    @Transactional
    public void addToCart(Long userId, CartItemPayload cartItemPayload) {
        try {

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

            for (CartItemRequest cartItemRequest : cartItemPayload.getCartItems()) {

                Products product = productRepository.findById(cartItemRequest.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + cartItemRequest.getProductId()));

                CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

                if (existingCartItem != null) {

                    existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
                    cartItemRepository.save(existingCartItem);
                } else {

                    CartItem cartItem = new CartItem();
                    cartItem.setUser(user);
                    cartItem.setProduct(product);
                    cartItem.setQuantity(cartItemRequest.getQuantity());

                    cartItemRepository.save(cartItem);
                }
            }
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("User or product not found", e);
        } catch (Exception e) {
            throw new RuntimeException("Error adding to cart", e);
        }
    }

}