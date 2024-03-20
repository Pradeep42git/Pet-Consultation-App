package com.onlinepetconsultation.exception;

public class UsersNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message="Admin not present";

	public UsersNotFoundException() {
		
	}

	public UsersNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
