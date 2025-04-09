package com.cns.productService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cns.productService.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
