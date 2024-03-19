package com.onlinepetconsultation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class ConsultantDto {

	
	@Size(min = 5, message = "Name should consist of at least 5 characters")
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid name format" )
	@NotNull
	private String name;
	
	@Email
	@Pattern(regexp = "^[A-Za-z0-9@.]*$" ,message = "Invalid email format" )
	@NotNull
	private String email;
	
	@NotNull
	private String address;
	
	@NotNull
	private String designation;
	
}
