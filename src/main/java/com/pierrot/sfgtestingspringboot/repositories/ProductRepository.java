package com.pierrot.sfgtestingspringboot.repositories;

import com.pierrot.sfgtestingspringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
