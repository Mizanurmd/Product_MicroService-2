package com.cns.productService.service;

import java.util.List;

import com.cns.productService.entities.Product;

public interface ProductService {
	List<Product>searchProduct(String keyword);
	Product saveProduct(Product product);

}
