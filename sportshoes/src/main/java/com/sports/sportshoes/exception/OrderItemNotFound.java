package com.sports.sportshoes.exception;

public class OrderItemNotFound extends RuntimeException {



	private static final long serialVersionUID = 1L;

	public OrderItemNotFound(String message) {
		super(message);
		System.out.println(message);
	}

}