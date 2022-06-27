package com.example.SellingBreadApp.repository;
import com.example.SellingBreadApp.entity.Product;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
