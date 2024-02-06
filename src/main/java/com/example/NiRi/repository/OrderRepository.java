package com.example.NiRi.repository;

import com.example.NiRi.modules.Order;
import com.example.NiRi.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByUser(User user);
}