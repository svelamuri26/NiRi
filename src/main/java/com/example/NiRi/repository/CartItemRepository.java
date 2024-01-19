package com.example.NiRi.repository;

import com.example.NiRi.modules.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long id);

    CartItem findByUserIdAndProductId(Long id, Long productId);
}