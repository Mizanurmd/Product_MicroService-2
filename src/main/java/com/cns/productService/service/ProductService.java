package com.cns.productService.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.productService.entities.Product;
import com.cns.productService.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// Get all products
	public List<Product>getAllProducts(){
		return productRepository.findAll();
		}
	
	// Get single product
	public Product getProduct(long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	// save product data into db
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}
	
}
