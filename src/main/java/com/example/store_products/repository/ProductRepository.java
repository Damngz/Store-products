package com.example.store_products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.store_products.models.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
  List<Product> findByCategoria(String categoria);
}
