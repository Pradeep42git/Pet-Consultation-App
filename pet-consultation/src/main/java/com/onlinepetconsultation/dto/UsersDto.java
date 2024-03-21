package com.onlinepetconsultation.dto;

import org.springframework.stereotype.Component;

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
@Component
public class UsersDto {

	@Size(min = 5, message = "Name should consist of at least 5 characters")
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$", message = "Invalid name format")
	@NotNull
	private String userName;

	@Email
	@Pattern(regexp = "^[A-Za-z0-9@.]*$", message = "Invalid email format")
	@NotNull
	private String userEmail;

	@NotNull
	@Size(min = 3, message = "Password should consist of at least 3 characters")
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$", message = "Invalid password format")
	private String userPassword;

	@Min(value = 6000000000l, message = "Phone number should range from 6000000000 to 9999999999")
	@Max(value = 9999999999l, message = "Phone number should range from 6000000000 to 9999999999")
	private long userPhone;

	private String userAddress;

}
