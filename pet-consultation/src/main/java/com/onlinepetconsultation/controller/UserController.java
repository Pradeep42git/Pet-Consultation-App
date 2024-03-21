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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinepetconsultation.dto.JWTResponse;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.dto.SignInRequest;
import com.onlinepetconsultation.dto.UsersDto;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.entity.Users;
import com.onlinepetconsultation.services.ConsultantService;
import com.onlinepetconsultation.services.ProductService;
import com.onlinepetconsultation.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/opc/user")
public class UserController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ConsultantService consultantService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/user-product-list")
	public ResponseEntity<ResponseStructure<List<Product>>> getForUser() {
		return productService.getAllProductByIsAvailable();
	}

	@GetMapping("/get-consultant-designation")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantByDesignation(
			@RequestParam String designation) {
		return consultantService.getConsultantsByDesignation(designation);
	}

	@GetMapping("/get-all-consultant")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultant() {
		return consultantService.getAllConsultantsForUsers();
	}

	@PostMapping("/save-user")
	public ResponseEntity<ResponseStructure<Users>> saveUser(@Valid @RequestBody UsersDto usersDto, BindingResult result) {
		
		if(result.hasErrors()) {
			String message = "";
			for(FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}
		
		return userService.saveUser(usersDto);

	}

	@GetMapping("/get-user-id/{userId}")
	public ResponseEntity<ResponseStructure<Users>> getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);

	}

	@GetMapping("/get-user-name/{userName}")
	public ResponseEntity<ResponseStructure<Users>> getAdminByName(@PathVariable String userName) {
		return userService.getUserByName(userName);

	}

	@DeleteMapping("/remove-user/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);

	}

	@PutMapping("/update-user/{userId}")
	public ResponseEntity<ResponseStructure<Users>> updateUser(@Valid @RequestBody UsersDto usersDto, BindingResult result,
			@PathVariable int userId) {
		
		if(result.hasErrors()) {
			String message = "";
			for(FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}
		
		return userService.updateUser(usersDto, userId);

	}
	
	@GetMapping("/get-all-user")
	public ResponseEntity<ResponseStructure<List<Users>>> getUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/user-login")
	public ResponseEntity<ResponseStructure<JWTResponse>> userLogin(@RequestBody SignInRequest request){
		return userService.userLogin(request);
	}

}
