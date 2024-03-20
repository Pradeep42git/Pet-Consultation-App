package com.onlinepetconsultation.exception;

public class ProductNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message="Product not found";

	public ProductNotExistException() {
		
	}

	public ProductNotExistException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
