package com.example.NiRi.repository;

import com.example.NiRi.modules.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void updateOrder(Order order) {
        entityManager.merge(order);
    }
}
