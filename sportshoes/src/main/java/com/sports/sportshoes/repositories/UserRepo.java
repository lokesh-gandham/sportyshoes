package com.sports.sportshoes.repositories;


import com.sports.sportshoes.models.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

void deleteUserById(Long id);
	
	Optional<User> findUserById(Long id);	
	User findByUsername(String username);

	

}