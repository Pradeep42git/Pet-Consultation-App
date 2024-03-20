package com.onlinepetconsultation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/opc/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ConsultantService consultantService;

	@PostMapping("/save-product/{adminId}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody ProductDto productDto,
			@PathVariable int adminId) {
		return productService.saveProduct(productDto, adminId);
	}

	@GetMapping("/admin-product-list")
	public ResponseEntity<ResponseStructure<List<Product>>> getForAdmin() {
		return productService.getAllProduct();
	}

	@PutMapping("/update-product/{adminId}/{productId}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int adminId,
			@RequestBody ProductDto updateProduct, @PathVariable int productId) {
		return productService.updateProduct(adminId, updateProduct, productId);
	}

	@DeleteMapping("/remove-product/{adminId}/{productId}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int adminId,
			@PathVariable int productId) {
		return productService.deleteProduct(adminId, productId);
	}

	@PostMapping("/save-consultant/{adminId}")
	public ResponseEntity<ResponseStructure<Consultant>> saveConsultant(@RequestBody ConsultantDto consultantDto,
			@PathVariable int adminId) {
		return consultantService.saveConsultant(consultantDto, adminId);
	}

	@GetMapping("/get-all-consultant")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultant() {
		return consultantService.getAllConsultantsForAdmin();
	}

	@PutMapping("/update-consultant/{adminId}/{consultantId}")
	public ResponseEntity<ResponseStructure<Consultant>> updateConsultant(@RequestBody ConsultantDto consultantDto,
			@PathVariable int adminId, @PathVariable int consultantId) {
		return consultantService.updateConsultant(consultantDto, adminId, consultantId);
	}

	@GetMapping("/get-all-bookings/{consultantId}/{adminId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(@PathVariable int consultantId,
			@PathVariable int adminId) {
		return consultantService.getAllBookings(consultantId, adminId);
	}

	@DeleteMapping("/remove-consultant/{adminId}/{consultantId}")
	public ResponseEntity<ResponseStructure<String>> removeConsultant(@PathVariable int adminId,
			@PathVariable int consultantId) {
		return consultantService.removeConsultant(consultantId, adminId);
	}

	@PostMapping("/save-admin")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody AdminDto adminDto) {
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
}
