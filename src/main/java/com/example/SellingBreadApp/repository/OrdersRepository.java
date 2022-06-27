package com.example.SellingBreadApp.repository;

import com.example.SellingBreadApp.entity.Orders;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {
    Page<Orders> findAll(Pageable pageable);
    Page<Orders> findAllByCreateDate(Date createDate, Pageable pageable);

    Page<Orders> findByCreateDateBetween(
        Date createDateStart,
        Date createDateEnd,
        Pageable pageable);
}