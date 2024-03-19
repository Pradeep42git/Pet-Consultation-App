package com.onlinepetconsultation.servicesimplementation;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.ProductDao;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.exception.AdminNotExistException;
import com.onlinepetconsultation.repository.AdminRepository;
import com.onlinepetconsultation.services.ProductService;

@Service
public class ProductServicesImp implements ProductService {

	private AdminRepository adminRepository;

	private ProductDao productDao;

	// Performs save operation and returns Item created Response
	public ResponseEntity<ResponseStructure<Product>> saveItem(Product product, int adminId) {

		Product recievedProduct = null;
		Optional<Admin> user = adminRepository.findById(adminId);
		if (user.isPresent()) {
			product.setAvailable(true);
			recievedProduct = productDao.createProduct(product);
		} else {
			throw new AdminNotExistException();
		}

		ResponseStructure<Product> response = new ResponseStructure<Product>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(recievedProduct);

		return new ResponseEntity<ResponseStructure<Product>>(response, HttpStatus.CREATED);

	}
}
