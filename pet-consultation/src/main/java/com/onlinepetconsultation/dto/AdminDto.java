package com.onlinepetconsultation.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

	
	@Size(min = 5, message = "Name should consist of at least 5 characters")
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid name format" )
	@NotNull
	private String name;
	@Email
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@.]*$" ,message = "Invalid email format" )
	@NotNull
	private String email;
	@NotNull
	@Size(min = 3, message = "Password should consist of at least 3 characters")
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid password format" )
	private String password;
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long phone;

}
