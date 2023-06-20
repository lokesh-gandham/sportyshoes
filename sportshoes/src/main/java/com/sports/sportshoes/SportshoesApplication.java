package com.sports.sportshoes;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SportshoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportshoesApplication.class, args);
	}

}
