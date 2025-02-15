package com.onlinepetconsultation.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ResponseStructure<T> {

	private int statusCode;
	private String message;
	private T data;
	
}
