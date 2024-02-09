package com.example.NiRi.service;

import com.example.NiRi.modules.OrderCreationRequest;
import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.Order;
import com.example.NiRi.modules.Products;
import com.example.NiRi.modules.User;
import com.example.NiRi.repository.OrderRepository;
import com.example.NiRi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserRepository userRepository;


    public double calculateTotalPriceForCartItems(List<CartItem> cartItems) {
        double totalPrice = 0;

        for (CartItem cartItem : cartItems) {
            Products product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            if (product != null) {
                double productPrice = product.getPrice();
                double subtotal = productPrice * quantity;
                totalPrice += subtotal;
            }
        }

        return totalPrice;
    }
    public Order convertCartToOrder(Long userId, List<Long> cartItemIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<CartItem> cartItems = cartItemService.getCartItemsByIds(cartItemIds);

        double totalPrice = calculateTotalPriceForCartItems(cartItems);

        Order order = new Order(user, cartItems, totalPrice, LocalDateTime.now());
        orderRepository.save(order);
        markCartItemsAsPurchased(cartItems, order);

        return order;
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public double calculateTotalPrice(Order order) {
        List<CartItem> cartItems = order.getCartItems();
        double totalPrice = 0.0;

        for (CartItem cartItem : cartItems) {
            Products product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            if (product != null) {
                double productPrice = product.getPrice();
                double subtotal = productPrice * quantity;
                totalPrice += subtotal;
            }
        }

        return totalPrice;
    }

    private void markCartItemsAsPurchased(List<CartItem> cartItems, Order order) {
        for (CartItem cartItem : cartItems) {
            cartItem.setOrder(order);
            cartItem.setStatus("Purchased");
        }
        SimpleJpaRepository cartItemRepository = null;
        cartItemRepository.saveAll(cartItems);
    }

    public List<Order> getOrdersByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        return orderRepository.findByUser(user);
    }

    public Order createOrder(OrderCreationRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderRequest.getUserId()));

        Order newOrder = new Order(user, null, orderRequest.getTotalPrice(), LocalDateTime.now());
        Order createdOrder = orderRepository.save(newOrder);

        return createdOrder;
    }
}