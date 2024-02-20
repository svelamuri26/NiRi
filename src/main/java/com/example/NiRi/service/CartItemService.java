package com.example.NiRi.service;

import com.example.NiRi.modules.*;
import com.example.NiRi.repository.CartItemRepository;
import com.example.NiRi.repository.OrderRepository;
import com.example.NiRi.repository.ProductRepository;
import com.example.NiRi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CartItemService {

    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

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

    public ResponseEntity<List<CartItem>> getCartItemsByUserId(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    public List<CartItem> getCartItemsBycartItemId(int cartItemId) {
        List<CartItem> cartItems = cartItemRepository.findListByCartItemId(cartItemId);
        return cartItems;
    }

    public ResponseEntity<String> removeFromCart(int cartItemId,Long productId) {
        try {
            List<CartItem> cartItems= getCartItemsBycartItemId(cartItemId);
            cartItems.stream().filter(cartItem -> cartItem.getProduct().getId().equals(productId)).findAny()
                    .ifPresent(cartItem -> cartItemRepository.delete(cartItem));

            return new ResponseEntity<>("Cart item removed successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Error removing cart item", e);
            return new ResponseEntity<>("Cart item not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error removing cart item", e);
            return new ResponseEntity<>("Error removing cart item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<List<CartItem>> getCartItemsByIds(List<Long> cartItemIds) {
        List<CartItem> cartItems = cartItemRepository.findAllById(cartItemIds);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> addToCart(Long userId, CartItemRequest cartItemRequest) {

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
                cartItem.setOrderId(cartItemRequest.getOrderId());
                cartItem.setCartItemId(cartItemRequest.getCartItemId());

                cartItemRepository.save(cartItem);
            }
            return new ResponseEntity<>("Item added to cart successfully", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Error adding to cart", e);
            return new ResponseEntity<>("User or product not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error adding to cart", e);
            return new ResponseEntity<>("Error adding to cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateCartItem(CartItemPayload  updateCartItemsRequest) {
        try {
            List<CartItem> cartItems = cartItemRepository.findListByCartItemId(updateCartItemsRequest.getCartItemId());
            cartItems.stream().filter(cartItem -> cartItem.getProduct().getId().equals(updateCartItemsRequest.getProductId())).findAny()
                    .ifPresent(cartItem -> cartItem.setQuantity(updateCartItemsRequest.getQuantity()));

                return new ResponseEntity<>("Cart items updated successfully", HttpStatus.OK);

            } catch (Exception e) {
            logger.error("Error updating cart item", e);
            return new ResponseEntity<>("Error updating cart item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public Order createAndSaveOrderWithCartItems(Long userId, List<CartItem> items) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Order order = new Order(user, items, calculateTotalPriceForCartItems(items), LocalDateTime.now(),1);

        order.setUser(user);

        orderRepository.save(order);

        for (CartItem item : items) {
            cartItemRepository.save(item);
        }

        return order;
    }

    private double calculateTotalPriceForCartItems(List<CartItem> items) {
        double totalPrice = 0.0;

        for (CartItem item : items) {
            Products product = item.getProduct();
            int quantity = item.getQuantity();

            if (product != null) {
                double productPrice = product.getPrice();
                double subtotal = productPrice * quantity;
                totalPrice += subtotal;
            }
        }

        return totalPrice;
    }

}
