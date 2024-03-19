package com.onlinepetconsultation.exception;

public class FoodOrderNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
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
