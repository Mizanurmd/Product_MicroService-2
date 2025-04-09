package com.cns.productService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cns.productService.entities.Product;
import com.cns.productService.service.ProductService;

@RestController
@RequestMapping("/proudcts")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;
		
	}
	
	// Controlling for all products
	@GetMapping
	public ResponseEntity<List<Product>>allProducts(){
		List<Product>list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	// Controlling for single product
	@GetMapping("/{id}")
	public ResponseEntity<Product>singleProduct(@PathVariable("id")long id){
		Product product = productService.getProduct(id);
		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
	}
	
	// Controlling for save product
	@PostMapping
	public ResponseEntity<Product>createProduct(@RequestBody Product product){
		Product prod = productService.saveProduct(product);
		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{/id}")
	public void deleteProduct(@PathVariable("id") long id){
		 productService.deleteProduct(id);
	}

}
