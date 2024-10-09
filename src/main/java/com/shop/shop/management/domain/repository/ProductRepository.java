package com.shop.shop.management.domain.repository;

import com.shop.shop.management.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName (String productName);
}
