package com.onlinepetconsultation.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	
	@Size(min = 5, message = "Name should consist of at least 5 characters")
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid name format" )
	private String name;
	private String description;
	private boolean isAvailable;
	private double totalCost;
}
