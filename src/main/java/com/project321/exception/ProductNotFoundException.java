package com.project321.exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException() {

	}
	
	public ProductNotFoundException(String message){
		super(message);
	}
}
