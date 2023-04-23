package com.paperized.productstore.repository;

import com.paperized.productstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
