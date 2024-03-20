package com.onlinepetconsultation.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message="Admin not present";

	public UserNotFoundException() {
		
	}

	public UserNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
