package com.onlinepetconsultation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Product findByName(String name);
	
	public List<Product> findByIsAvailable(boolean status);
}
