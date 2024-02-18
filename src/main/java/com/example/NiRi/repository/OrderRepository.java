package com.example.NiRi.repository;

import com.example.NiRi.modules.CartItem;
import com.example.NiRi.modules.Order;
import com.example.NiRi.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    Order findByOrderId(int orderId);

    List<Order> findByUser(User user);

    Optional findPendingOrderByUserId(Long userId);
}