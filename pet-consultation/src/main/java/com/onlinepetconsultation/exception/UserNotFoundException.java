package com.onlinepetconsultation.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
	String message="";
	
	@Override
	public String getMessage() {
		return message;
	}

}
