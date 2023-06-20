package com.sports.sportshoes.repositories;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sports.sportshoes.models.*;



@Repository
public interface OrderRepo extends JpaRepository<OrderItem , Long>{

	 @Query("SELECT o FROM OrderItem o  WHERE " +"o.category LIKE CONCAT('%',:query, '%')")
	 List<OrderItem> search(String query);
	}

	
	