package com.onlinepetconsultation.exception;

public class AdminNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message="Admin not present";

	public AdminNotExistException() {
		
	}

	public AdminNotExistException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
