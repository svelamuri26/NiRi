package com.example.NiRi.controller;

import com.example.NiRi.modules.CartItemPayload;
import com.example.NiRi.modules.CartItemRequest;
import com.example.NiRi.modules.Order;
import com.example.NiRi.modules.OrderResponse;
import com.example.NiRi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<com.example.NiRi.modules.Order> createOrder(@RequestBody CartItemPayload cartItemPayload) {
        try {
            com.example.NiRi.modules.OrderCreationRequest orderCreationRequest =
                    new com.example.NiRi.modules.OrderCreationRequest();
            orderCreationRequest.setUserId(cartItemPayload.getUserId());

            List<Long> cartItemIds = cartItemPayload.getCartItems().stream()
                    .map(CartItemRequest::getProductId)
                    .collect(Collectors.toList());

            orderCreationRequest.setCartItemIds(cartItemIds);

            com.example.NiRi.modules.Order createdOrder = orderService.createOrder(orderCreationRequest);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getByUserId")
    public List<Order> getOrdersByUserId(@RequestParam Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/getById")
    public ResponseEntity<OrderResponse> getOrderById(@RequestParam Long id) {
        Order order = orderService.getOrderById(id);

        if (order != null) {
            double totalPrice = orderService.calculateTotalPrice(order);
            OrderResponse orderResponse = new OrderResponse(order, totalPrice);
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }
}