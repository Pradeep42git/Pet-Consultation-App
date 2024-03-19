package com.onlinepetconsultation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.onlinepetconsultation.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFoundExceptionhandler(UserNotFoundException exception){
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
		responseStructure.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> consultantNotFoundExceptionhandler(ConsultantNotFoundException exception){
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
		responseStructure.setMessage(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
	
}
