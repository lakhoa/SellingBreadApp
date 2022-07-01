package com.example.SellingBreadApp.repository;
import com.example.SellingBreadApp.entity.Orders;
import org.springframework.stereotype.Repository;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {
    Page<Orders> findAll(Pageable pageable);
    Page<Orders> findAllByCreateDate(Date createDate, Pageable pageable);

    Page<Orders> findByCreateDateBetween(
        Date createDateStart,
        Date createDateEnd,
        Pageable pageable);
}
