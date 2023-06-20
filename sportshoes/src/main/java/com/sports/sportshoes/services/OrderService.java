package com.sports.sportshoes.services;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.sportshoes.exception.OrderItemNotFound;
import com.sports.sportshoes.models.*;
import com.sports.sportshoes.repositories.*;

@Service
public class OrderService {

	private final OrderRepo OrderRepo;
	
	@Autowired
	public OrderService(OrderRepo orderrepo)
	{
		this.OrderRepo=orderrepo;
	}
	public OrderItem addorder(OrderItem product) {        
        return OrderRepo.save(product);
    }

	public OrderItem getorderbyid(Long id) {		
		return OrderRepo.findById(id).get();
	}
	
	public List<OrderItem> findAllorderitems(){
		return OrderRepo.findAll();
	}
	

	
	
	
	
	
	public OrderItem findcategory(Long id){
		return OrderRepo.findById(id)
				.orElseThrow(() -> new OrderItemNotFound("order by category" + id + " was not found"));
	}
	
	  public List<OrderItem> listAll(String keyword) {
	        if (keyword != null) {
	          return  OrderRepo.search(keyword);
	         
	        }
	        return OrderRepo.findAll();
	       
	  }
	
	
}