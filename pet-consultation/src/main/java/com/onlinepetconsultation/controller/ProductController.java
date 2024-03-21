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

import com.onlinepetconsultation.dto.ProductDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.services.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/onlinepetconsultantion/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/admins")
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

	@GetMapping("/admins/products")
	public ResponseEntity<ResponseStructure<List<Product>>> getForAdmin() {
		return productService.getAllProduct();
	}

	@GetMapping("/users/products")
	public ResponseEntity<ResponseStructure<List<Product>>> getForUser() {
		return productService.getAllProductByIsAvailable();
	}

	@PutMapping("/admins/{productId}")
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

	@DeleteMapping("/admins/{productId}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int productId) {
		return productService.deleteProduct(productId);
	}

}
