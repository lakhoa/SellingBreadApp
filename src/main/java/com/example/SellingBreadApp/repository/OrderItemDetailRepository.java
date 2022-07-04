package com.example.SellingBreadApp.repository;

import com.example.SellingBreadApp.entity.OrderItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDetailRepository extends JpaRepository<OrderItemDetail, Long> {

}
