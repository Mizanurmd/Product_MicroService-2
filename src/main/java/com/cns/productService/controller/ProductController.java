package com.cns.productService.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cns.productService.entities.Product;
import com.cns.productService.service.ProductService;



@RestController
@RequestMapping("/products")
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
		List<Product>searchProduct = productService.searchProduct(keyword);
		return new ResponseEntity<List<Product>>(searchProduct, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?>createProduct(
			@RequestParam("name")String name,
			@RequestParam("description")String description,
			@RequestParam("brand")String brand,
			@RequestParam("quantity")int quantity,
			@RequestParam("price")double price,
			@RequestParam("active")boolean active,
			@RequestParam("relaseDate")@DateTimeFormat(pattern = "dd-MM-yyyy")Date relaseDate,
			@RequestParam("image")MultipartFile image
			){
		Product p = new Product();
		p.setName(name);
		p.setDescription(description);
		p.setBrand(brand);
		p.setQuantity(quantity);
		p.setPrice(price);
		p.setActive(active);
		p.setRelaseDate(relaseDate);
		if(image !=null && !image.isEmpty()) {
			try {
				p.setImage(image.getBytes());
				p.setImageName(image.getOriginalFilename());
				p.setImageType(image.getContentType());
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		Product saveProduct = productService.saveProduct(p);
		System.out.println("Product"+ saveProduct);
		return ResponseEntity.ok(saveProduct);
	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>>getAllProducts(){
		List<Product>pdList =productService.allProducts();
		System.out.println("Get all Product : "+ pdList);
		return new ResponseEntity<List<Product>>(pdList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product>getProductById(@PathVariable("id")long id){
		Product productId = productService.getOneProduct(id);
		return new ResponseEntity<Product>(productId,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void getDeletedProductById(@PathVariable("id")long id){
		productService.removeProduct(id);
	
	}
	
	@PutMapping("/update")
	public ResponseEntity<Product>updateProduct(
			@RequestParam("id")long id,
			@RequestParam("name")String name,
			@RequestParam("description")String description,
			@RequestParam("brand")String brand,
			@RequestParam("quantity")int quantity,
			@RequestParam("price")double price,
			@RequestParam("active")boolean active,
			@RequestParam("relaseDate")@DateTimeFormat(pattern = "dd-MM-yyyy")Date relaseDate,
			@RequestParam("image")MultipartFile image
			) throws IOException{
		
		Product updateProduct = productService.getOneProduct(id);
		if(name !=null)updateProduct.setName(name);
		if(description !=null)updateProduct.setDescription(description);
		if(brand !=null)updateProduct.setBrand(brand);
		if(quantity !=0)updateProduct.setQuantity(quantity);
		if(price !=0)updateProduct.setPrice(price);
		if(active !=false)updateProduct.setActive(active);
		if(relaseDate !=null)updateProduct.setRelaseDate(relaseDate);
		
		if(image !=null && !image.isEmpty()) {
			updateProduct.setImage(image.getBytes());
			updateProduct.setImageName(image.getOriginalFilename());
			updateProduct.setImageType(image.getContentType());
		}
		
		Product updatedProduct2 = productService.updateProduct(id, updateProduct);
		
		return new ResponseEntity<Product>(updatedProduct2, HttpStatus.ACCEPTED);
		
	}
	
	// Search product using SQL native query
	
	@GetMapping("/searchSQL")
	public ResponseEntity<List<Product>>getSearchSQL(@RequestParam("searchQuery") String quesearchQuery){
		List<Product>searchProduct = productService.searchUsingSQL(quesearchQuery);
		System.out.println("SQL Search ===::"+ searchProduct);
		return new ResponseEntity<List<Product>>(searchProduct, HttpStatus.OK);
		
	}
	

}
