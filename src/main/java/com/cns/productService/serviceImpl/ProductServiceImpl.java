package com.cns.productService.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.productService.entities.Product;
import com.cns.productService.repository.ProductRepository;
import com.cns.productService.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository =productRepository;
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		List<Product> productList = productRepository.searchProduct(keyword);
		return null;
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	

}
