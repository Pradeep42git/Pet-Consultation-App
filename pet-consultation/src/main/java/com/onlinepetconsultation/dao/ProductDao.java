package com.onlinepetconsultation.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.repository.ProductRepository;

@Repository
public class ProductDao {
	
	
	@Autowired
	private ProductRepository productRepository;

	/*
	 * This method is used to create and return the product /
	 * and also update the details of the product
	 */
	public Product createProduct(Product product) {
		
		if(product!=null) {
			return productRepository.save(product);
		}
		return null;
	}
	
	/*
	 * This method is used to get details of the Product by Product Id
	 */
	public Product getProductById(int productId) {
		Optional<Product> optional=productRepository.findById(productId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	/*
	 * This method is used to get details of the Product by Product Name
	 */
	public Product getProductByName(String name) {
		Product product=productRepository.findByName(name);
		if(product!=null) {
			return product;
		}
		return null;
	}
	
	/*
	 * This method is used to delete the product from the product table
	 */
	public boolean removeProduct(int productId) {
		Product product=this.getProductById(productId);
		if(product!=null) {
			productRepository.delete(product);
			return true;
		}
		return false;
	}
	
	
}
