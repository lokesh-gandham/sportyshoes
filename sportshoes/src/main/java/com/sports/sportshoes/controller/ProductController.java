package com.sports.sportshoes.controller;

import com.sports.sportshoes.models.*;

import com.sports.sportshoes.services.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

	private final ProductService productService;
	

	public ProductController(ProductService productService) {		
		this.productService = productService;
	}

	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.findAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}	
		
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById (@PathVariable("id") Long id){
		Product product = productService.findProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}	
	
	@PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
		System.out.println("Inside addProduct() in the Product Controller");
		System.out.println("Passed in Product object\n" + product.toString());
		Product newProduct = product;		
		System.out.println("newProduct:\n" + newProduct.toString());
		productService.addProduct(newProduct);
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/product/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") Long id){
		System.out.println("Product Passed In:\n" + product.toString());
		System.out.println("Id Passed In:" + id);
		Product updateProduct = productService.updateProduct(product);
		return new ResponseEntity<>(updateProduct, HttpStatus.OK);
	}	
	
	@DeleteMapping("/product/delete/{id}")	
    public ResponseEntity<?> deleteproduct(@PathVariable("id") Long id){
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
}
}