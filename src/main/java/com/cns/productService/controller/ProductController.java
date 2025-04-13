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
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping("/search")
	public ResponseEntity<List<Product>>getSerachProduct(
			 @RequestParam("keyword")String keyword)
	{
		List<Product>searchProduc = productService.searchProduct(keyword);
		return new ResponseEntity<List<Product>>(searchProduc, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Product>createProduct(@RequestBody Product product){
		Product saveProduct = productService.saveProduct(product);
		return new ResponseEntity<Product>(saveProduct, HttpStatus.CREATED);
	}
	
	

}
