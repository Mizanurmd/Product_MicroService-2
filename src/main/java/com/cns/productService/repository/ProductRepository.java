package com.cns.productService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cns.productService.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :keyword, '%')"
			+ " OR p.description LIKE CONCAT('%', :keyword, '%')")
	List<Product> searchProduct(@Param("keyword") String keyword);

	
	@Query(value="SELECT p FROM Product p WHERE"+
			"p.name LIKE CONCAT('%',:keyword, '%')"+
			"Or p.description LIKE CONCAT('%',:keyword, '%')", nativeQuery = true	
					)
			List<Product>searchProductSQL(@Param("keyword") String keyword);

}
