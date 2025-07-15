package com.project321.controller;

public class ProductNotFound extends RuntimeException{

	public ProductNotFound() {

	}
	public ProductNotFound(String message) {
		super(message);
	}

}
