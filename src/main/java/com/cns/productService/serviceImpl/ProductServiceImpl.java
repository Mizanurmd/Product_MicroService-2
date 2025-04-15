package com.cns.productService.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Override
	public List<Product> searchProduct(String keyword) {
		List<Product> productList = productRepository.searchProduct(keyword);
		return productList;
	}

	@Override
	public Product saveProduct(Product product){

	        return productRepository.save(product);
	    
	}

	@Override
	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getOneProduct(long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product updateProduct(long id, Product product) {

		Optional<Product> optioanl = productRepository.findById(id);
		if(optioanl.isPresent()) {
			Product existingProduct = optioanl.get();
			existingProduct.setName(product.getName());
			existingProduct.setDescription(product.getDescription());
			existingProduct.setBrand(product.getBrand());
			existingProduct.setQuantity(product.getQuantity());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setActive(product.isActive());
			existingProduct.setRelaseDate(product.getRelaseDate());
			
			if(existingProduct.getImage() != null && existingProduct.getImage().length > 0) {
				existingProduct.setImage(product.getImage());
				existingProduct.setImageName(product.getImageName());
				existingProduct.setImageType(product.getImageType());
			}
			
			
			return productRepository.save(existingProduct);
			
		}
		return product;
		
	}

	@Override
	public void removeProduct(long id) {
		productRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public List<Product> searchUsingSQL(String searchQuery) {
		return productRepository.searchProductSQL(searchQuery);
	}
	
	

}
