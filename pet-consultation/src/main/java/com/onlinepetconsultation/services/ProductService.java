package com.onlinepetconsultation.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.ProductDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Product;

public interface ProductService {

	/*
	 * Performs save operation and returns Product created Response
	 */
	ResponseEntity<ResponseStructure<Product>> saveProduct(ProductDto productDto);

	/*
	 * Performs get operation and returns Product List fetched Response for Users
	 */
	ResponseEntity<ResponseStructure<List<Product>>> getAllProductByIsAvailable();

	/*
	 * Performs get operation and returns Product List fetched Response for Admin
	 */
	ResponseEntity<ResponseStructure<List<Product>>> getAllProduct();

	/*
	 * Performs update operation and returns the updated Product Response for Admin
	 */
	ResponseEntity<ResponseStructure<Product>> updateProduct(ProductDto updateProduct, int productId);

	/*
	 * Performs delete operations by setting field in false
	 */
	ResponseEntity<ResponseStructure<String>> deleteProduct(int productId);
}
