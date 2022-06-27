package com.example.SellingBreadApp.repository;

import com.example.SellingBreadApp.entity.OrderItem;
import com.example.SellingBreadApp.entity.OrderItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDetailRepository extends JpaRepository<OrderItemDetail, Long> {
}
