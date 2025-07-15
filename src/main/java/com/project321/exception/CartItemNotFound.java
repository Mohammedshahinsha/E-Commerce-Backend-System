package com.project321.exception;

public class CartItemNotFound extends RuntimeException{

	public CartItemNotFound() {

	}
	
	public CartItemNotFound(String message) {
		super(message);
	}
}
