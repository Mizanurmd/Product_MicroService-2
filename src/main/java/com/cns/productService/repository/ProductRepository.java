package com.cns.productService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cns.productService.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE " +
		       "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "STR(p.quantity) LIKE CONCAT('%', :keyword, '%') OR " +
		       "STR(p.price) LIKE CONCAT('%', :keyword, '%') OR " +
		       "LOWER(p.imageName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(p.imageType) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		List<Product> searchProduct(@Param("keyword") String keyword);



	@Query(value = "SELECT * FROM product WHERE " +
		       "LOWER(name) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		       "LOWER(description) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		       "LOWER(brand) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		       "CAST(quantity AS CHAR) LIKE CONCAT('%', :searchQuery, '%') OR " +
		       "CAST(price AS CHAR) LIKE CONCAT('%', :searchQuery, '%') OR " +
		       "LOWER(image_name) LIKE LOWER(CONCAT('%', :searchQuery, '%')) OR " +
		       "LOWER(image_type) LIKE LOWER(CONCAT('%', :searchQuery, '%'))", nativeQuery = true)
		List<Product> searchProductSQL(@Param("searchQuery") String searchQuery);



}
