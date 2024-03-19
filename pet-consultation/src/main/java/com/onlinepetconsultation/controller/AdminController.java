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

import com.onlinepetconsultation.dto.ProductDto;
import com.onlinepetconsultation.dto.ResponseStructure;
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
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody ProductDto productDto,@PathVariable int adminId){
		return productService.saveProduct(productDto, adminId);
	}
	
	@GetMapping("/admin-product-list")
	public ResponseEntity<ResponseStructure<List<Product>>> getForAdmin(){
		return productService.getAllProduct();
	}
	
	@PutMapping("/update-product/{adminId}/{productId}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int adminId,@RequestBody ProductDto updateProduct,@PathVariable int productId){
		return productService.updateProduct(adminId, updateProduct, productId);
	}
	
	@DeleteMapping("/remove-product/{adminId}/{productId}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int adminId,@PathVariable int productId){
		return productService.deleteProduct(adminId, productId);
	}
}
