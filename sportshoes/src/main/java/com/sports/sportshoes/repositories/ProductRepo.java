package com.sports.sportshoes.repositories;


import com.sports.sportshoes.models.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{


	

}
