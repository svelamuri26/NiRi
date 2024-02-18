package com.example.NiRi.controller;

import com.example.NiRi.modules.*;
import com.example.NiRi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.NiRi.repository.CartItemRepository;


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


    /*
    @PostMapping("/create")
    public ResponseEntity<List<Order>> createOrder(@RequestBody List<CartItemPayload> cartItemPayloadList) {
        try {
            List<OrderCreationRequest> orderCreationRequests = cartItemPayloadList.stream()
                    .map(cartItemPayload -> {
                        OrderCreationRequest orderCreationRequest = new OrderCreationRequest();
                        orderCreationRequest.setUserId(cartItemPayload.getUserId());
                        List<Long> cartItemIds = cartItemPayload.getCartItems().stream()
                                .map(CartItemRequest::getProductId)
                                .collect(Collectors.toList());
                        orderCreationRequest.setCartItemIds(cartItemIds);
                        return orderCreationRequest;
                    })
                    .collect(Collectors.toList());

            List<Order> createdOrders = orderCreationRequests.stream()
                    .map(orderService::createOrder)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(createdOrders, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestPayload orderRequest) {
        try {
            System.out.println("order11::***********");
            System.out.println(orderRequest.getCartItemId());
            //OrderRequest orderRequest = new OrderRequest(orderRequestPayload.getUserId(),orderRequestPayload.getCartItemId(),orderRequestPayload.getOrderId());
            Order order = orderService.convertCartToOrder(orderRequest.getUserId(),orderRequest.getCartItemId(),orderRequest.getOrderId());

            return  new ResponseEntity<>(order, HttpStatus.CREATED);

        } catch (Exception e) {
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