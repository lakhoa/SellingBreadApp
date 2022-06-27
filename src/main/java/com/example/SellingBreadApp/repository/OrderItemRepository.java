package com.example.SellingBreadApp.repository;

import com.example.SellingBreadApp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
