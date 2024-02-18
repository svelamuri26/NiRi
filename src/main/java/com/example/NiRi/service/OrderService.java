package com.example.NiRi.service;

import com.example.NiRi.modules.OrderCreationRequest;
import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.Order;
import com.example.NiRi.modules.Products;
import com.example.NiRi.modules.User;
import com.example.NiRi.repository.OrderRepository;
import com.example.NiRi.repository.UserRepository;
import com.example.NiRi.repository.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    public double calculateTotalPriceForCartItems(List<CartItem> cartItems) {
        System.out.println("orderPrice221::***********");
        double totalPrice = 0;

        for (CartItem cartItem : cartItems) {
            Products product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            System.out.println(quantity);

            if (product != null) {
                double productPrice = product.getPrice();
                System.out.println(productPrice);
                double subtotal = productPrice * quantity;
                totalPrice += subtotal;
            }
        }

        return Math.round(totalPrice * 100.0) / 100.0;
    }
    /*
    public ResponseEntity<Order> convertCartToOrder(Long userId, List<Long> cartItemIds) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

            List<CartItem> cartItems = (List<CartItem>) cartItemService.getCartItemsByIds(cartItemIds);

            double totalPrice = calculateTotalPriceForCartItems(cartItems);

            Order order = new Order(user, cartItems, totalPrice, LocalDateTime.now());

            orderRepository.save(order);
            markCartItemsAsPurchased(cartItems, order);

            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

    public Order convertCartToOrder(Long userId, int cartItemId,int orderId) {
            System.out.println("order221::***********");
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
            System.out.println(user);
            System.out.println("order331::***********");
            List<CartItem> cartItems = (List<CartItem>) cartItemService.getCartItemsBycartItemId(cartItemId);
            System.out.println(cartItems);
            System.out.println("order336::***********");
            double totalPrice = calculateTotalPriceForCartItems(cartItems);
            System.out.println("orderPrice116::***********");
            System.out.println(totalPrice);
            Order order = new Order(user, cartItems, totalPrice, LocalDateTime.now(),orderId);
            System.out.println("order11114::***********");
            System.out.println(order.getId());
            System.out.println(order.getTotalPrice());
            System.out.println(order.getUser().getId());
            orderRepository.save(order);
            System.out.println("order11115::***********");
            markCartItemsAsPurchased(cartItems, orderId);
            System.out.println("order11116::***********");


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

    private void markCartItemsAsPurchased(List<CartItem> cartItems, int order) {
        for (CartItem cartItem : cartItems) {
            System.out.println("order11117::***********");
            System.out.println(cartItem.getStatus());
            //cartItem.setOrderId(order);
            cartItem.setStatus("Purchased");
            cartItemRepository.save(cartItem);
        }
        //SimpleJpaRepository cartItemRepository = null;
        //cartItemRepository.saveAll(cartItems);
    }

    public List<Order> getOrdersByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        return orderRepository.findByUser(user);
    }

    public Order createOrder(OrderCreationRequest orderRequest) {
        Logger logger = LoggerFactory.getLogger(getClass());

        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + orderRequest.getUserId()));

        logger.info("Order Creation Request: {}", orderRequest);
        Order newOrder = new Order(user, null, orderRequest.getTotalPrice(), LocalDateTime.now(), orderRequest.getOrderId());
        Order createdOrder = orderRepository.save(newOrder);
        logger.info("Created Order: {}", createdOrder);

        return createdOrder;
    }

}