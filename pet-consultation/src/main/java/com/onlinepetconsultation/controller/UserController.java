package com.onlinepetconsultation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinepetconsultation.dto.JWTResponse;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.dto.SignInRequest;
import com.onlinepetconsultation.dto.UsersDto;
import com.onlinepetconsultation.entity.Users;
import com.onlinepetconsultation.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/onlinepetconsultantion/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiResponse(description = "User saved",responseCode = "201" )
	@Operation(summary = "To save a user", description = "To save a user")
	@PostMapping
	public ResponseEntity<ResponseStructure<Users>> saveUser(@Valid @RequestBody UsersDto usersDto,
			BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return userService.saveUser(usersDto);

	}

	@ApiResponse(description = "User found",responseCode = "200" )
	@Operation(summary = "To get details of a user by ID", description = "To get details of a user by ID")
	@GetMapping("/admins/getUserById/{userId}")
	public ResponseEntity<ResponseStructure<Users>> getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);

	}
	
	@ApiResponse(description = "User found",responseCode = "200" )
	@Operation(summary = "To get details of a user by user name", description = "To get details of a user by user name")
	@GetMapping("/admins/getUserByName/{userName}")
	public ResponseEntity<ResponseStructure<Users>> getUserByName(@PathVariable String userName) {
		return userService.getUserByName(userName);
	}

	@ApiResponse(description = "User deleted",responseCode = "200" )
	@Operation(summary = "To delete details of a user by ID", description = "To delete details of a user by ID")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);

	}

	@ApiResponse(description = "User updated",responseCode = "200" )
	@Operation(summary = "To update details of a user by ID", description = "To update details of a user by ID")
	@PutMapping("/{userId}")
	public ResponseEntity<ResponseStructure<Users>> updateUser(@Valid @RequestBody UsersDto usersDto,
			BindingResult result, @PathVariable int userId) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return userService.updateUser(usersDto, userId);

	}

	@ApiResponse(description = "User List",responseCode = "200" )
	@Operation(summary = "To get all users", description = "To get all users present in DB as list")
	@GetMapping("/admins/getAllUsers")
	public ResponseEntity<ResponseStructure<List<Users>>> getUsers() {
		return userService.getAllUsers();
	}

	@ApiResponse(description = "Login Success",responseCode = "200" )
	@Operation(summary = "To Login as User", description = "To Login as User")
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<JWTResponse>> userLogin(@RequestBody SignInRequest request) {
		return userService.userLogin(request);
	}

}
