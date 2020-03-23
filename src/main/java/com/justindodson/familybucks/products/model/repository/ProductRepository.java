package com.justindodson.familybucks.products.model.repository;

import com.justindodson.familybucks.products.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
