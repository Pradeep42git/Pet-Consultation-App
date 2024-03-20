package com.onlinepetconsultation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Food_ProductDtoResponse {
	
	private String message;
	private String product_name;
	
	public Food_ProductDtoResponse(String message, String product_name) {
		this.message = message;
		this.product_name = product_name;
	}
	

}
