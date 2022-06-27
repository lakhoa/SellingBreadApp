package com.example.SellingBreadApp.repository;

import com.example.SellingBreadApp.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
