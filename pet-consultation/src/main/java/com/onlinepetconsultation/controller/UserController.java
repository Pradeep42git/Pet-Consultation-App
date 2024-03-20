package com.onlinepetconsultation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.services.ConsultantService;
import com.onlinepetconsultation.services.ProductService;

@RestController
@RequestMapping("/opc/user")
public class UserController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ConsultantService consultantService;

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

}
