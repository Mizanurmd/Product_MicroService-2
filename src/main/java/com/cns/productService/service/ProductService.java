package com.cns.productService.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cns.productService.entities.Product;

public interface ProductService {
	List<Product>searchProduct(String keyword);
	Product saveProduct(Product product);
	List<Product>allProducts();
	Product getOneProduct(long id);
	Product updateProduct(long id, Product product);
	void removeProduct(long id);

}
