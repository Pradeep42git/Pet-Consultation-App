package com.onlinepetconsultation.exception;

public class FoodOrderNotFoundException extends RuntimeException{
	
	String message="";
	public FoodOrderNotFoundException(String message) {
		super();
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}

}
