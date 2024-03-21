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

import com.onlinepetconsultation.dto.AdminDto;
import com.onlinepetconsultation.dto.ConsultantDto;
import com.onlinepetconsultation.dto.ProductDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.services.AdminService;
import com.onlinepetconsultation.services.ConsultantService;
import com.onlinepetconsultation.services.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/opc/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ConsultantService consultantService;

	@PostMapping("/save-product")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@Valid @RequestBody ProductDto productDto,
			BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return productService.saveProduct(productDto);
	}

	@GetMapping("/admin-product-list")
	public ResponseEntity<ResponseStructure<List<Product>>> getForAdmin() {
		return productService.getAllProduct();
	}

	@PutMapping("/update-product/{productId}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@Valid @RequestBody ProductDto updateProduct,
			BindingResult result, @PathVariable int productId) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return productService.updateProduct(updateProduct, productId);
	}

	@DeleteMapping("/remove-product/{productId}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int productId) {
		return productService.deleteProduct(productId);
	}

	@PostMapping("/save-consultant")
	public ResponseEntity<ResponseStructure<Consultant>> saveConsultant(@Valid @RequestBody ConsultantDto consultantDto,
			BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return consultantService.saveConsultant(consultantDto);
	}

	@GetMapping("/get-all-consultant")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultant() {
		return consultantService.getAllConsultantsForAdmin();
	}

	@PutMapping("/update-consultant/{consultantId}")
	public ResponseEntity<ResponseStructure<Consultant>> updateConsultant(
			@Valid @RequestBody ConsultantDto consultantDto, BindingResult result, @PathVariable int consultantId) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return consultantService.updateConsultant(consultantDto, consultantId);
	}

	@GetMapping("/get-all-bookings/{consultantId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(@PathVariable int consultantId) {
		return consultantService.getAllBookings(consultantId);
	}

	@DeleteMapping("/remove-consultant/{consultantId}")
	public ResponseEntity<ResponseStructure<String>> removeConsultant(@PathVariable int consultantId) {
		return consultantService.removeConsultant(consultantId);
	}

	@PostMapping("/save-admin")
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

	@GetMapping("/get-admin-id/{adminId}")
	public ResponseEntity<ResponseStructure<Admin>> getAdminById(@PathVariable int adminId) {
		return adminService.getById(adminId);

	}

	@GetMapping("/get-admin-name/{adminName}")
	public ResponseEntity<ResponseStructure<Admin>> getAdminByName(@PathVariable String adminName) {
		return adminService.getByName(adminName);

	}

	@DeleteMapping("/remove-admin/{adminId}")
	public ResponseEntity<ResponseStructure<String>> deleteAdmin(@PathVariable int adminId) {
		return adminService.deleteAdmin(adminId);

	}

	@PutMapping("/update-admin/{adminId}")
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
}
