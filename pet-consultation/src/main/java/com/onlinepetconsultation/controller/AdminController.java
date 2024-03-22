package com.onlinepetconsultation.controller;

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

import com.onlinepetconsultation.dto.AdminDto;
import com.onlinepetconsultation.dto.JWTResponse;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.dto.SignInRequest;
import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.services.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/onlinepetconsultantion/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@ApiResponse(description = "User saved as a Admin",responseCode = "201" )
	@Operation(summary = "To save User as Admin", description = "To save User as Admin")
	@PostMapping
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@Valid @RequestBody AdminDto adminDto,
			BindingResult result) {
		
		if(result.hasErrors()) {
			String message = "";
			for(FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}
		
		return adminService.saveAdmin(adminDto);

	}

	@ApiResponse(description = "Admin found",responseCode = "200" )
	@Operation(summary = "To find Admin details by ID", description ="To find Admin details based on ID")
	@GetMapping("/getAdminId/{adminId}")
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(@PathVariable int adminId) {
		return adminService.getById(adminId);

	}

	@ApiResponse(description = "Admin found",responseCode = "200" )
	@Operation(summary = "To find Admin details by Name", description ="To find Admin details based on their Name")
	@GetMapping("/getAdminName/{adminName}")
	public ResponseEntity<ResponseStructure<Admin>> getAdminByName(@PathVariable String adminName) {
		return adminService.getByName(adminName);

	}
	@ApiResponse(description = "Admin deleted",responseCode = "200" )
	@Operation(summary = "To delete Admin details by ID", description ="To delete Admin details based on ID")
	@DeleteMapping("/{adminId}")
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable int adminId) {
		return adminService.deleteAdmin(adminId);

	}

	@ApiResponse(description = "Admin updated",responseCode = "200" )
	@Operation(summary = "To update Admin details by ID", description ="To update Admin details based on ID")
	@PutMapping("/{adminId}")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@Valid @RequestBody AdminDto adminDto,
			BindingResult result, @PathVariable int adminId) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return adminService.updateAdmin(adminDto, adminId);

	}
	
	@ApiResponse(description = "Login Success",responseCode = "200" )
	@Operation(summary = "To Login as Admin", description = "To Login as Admin")
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<JWTResponse>> adminLogin(@RequestBody SignInRequest request){
		return adminService.adminLogin(request);
	}
	
	
}
