package com.example.NiRi.service;

import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.Order;
import com.example.NiRi.modules.Products;
import com.example.NiRi.modules.User;
import com.example.NiRi.repository.CartItemRepository;
import com.example.NiRi.repository.OrderRepository;
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

    public Order convertCartToOrder(Long userId, List<Long> cartItemIds) {

        User user = new User();
        List<CartItem> cartItems = cartItemService.getCartItemsByIds(cartItemIds);
        Order order = new Order();
        order.setUser(user);
        order.setCartItems(cartItems);
        order.setTotalPrice(calculateTotalPrice(cartItems));
        order.setOrderDate(LocalDateTime.now());
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

    private long calculateTotalPrice(List<CartItem> cartItems) {
        long totalPrice = 0;

        for (CartItem cartItem : cartItems) {
            Products product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            if (product != null) {
                float productPrice = product.getPrice();
                float subtotal = productPrice * quantity;
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
}

