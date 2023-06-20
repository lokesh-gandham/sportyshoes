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
@RequestMapping("/api/admin/OrderItems")
public class OrderController {

	private final OrderService OrderService;
	

	public OrderController(OrderService OrderService ) {		
		this.OrderService = OrderService;
	}

	
	@GetMapping("/all")
	public ResponseEntity<List<OrderItem>> getAllorders(){
		List<OrderItem> orders = OrderService.findAllorderitems();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}	
		
	@GetMapping("/orderitem/{id}")
	public ResponseEntity<OrderItem> getProductById (@PathVariable("id") Long id){
		OrderItem orders = OrderService.getorderbyid(id);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}	
	
	@PostMapping("/add")
    public ResponseEntity<OrderItem> addorder(@RequestBody OrderItem orderitem){
		System.out.println("Inside addorder() in the OrderItem Controller");
		System.out.println("Passed in Product object\n" + orderitem.toString());
		OrderItem newOrder = orderitem;		
		System.out.println("newProduct:\n" + newOrder.toString());
		OrderService.addorder(newOrder);
		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}
	
	
	

	@RequestMapping("/search/{keyword}")
	 public List<OrderItem> search(@PathVariable("keyword") String keyword){
		
	         List<OrderItem> listorderitems = OrderService.listAll(keyword);
	   
	        return listorderitems;
	    
	    }
	
	

}