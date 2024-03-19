package com.onlinepetconsultation.servicesimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.ProductDao;
import com.onlinepetconsultation.dto.ProductDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.exception.AdminNotExistException;
import com.onlinepetconsultation.exception.ProductNotExistException;
import com.onlinepetconsultation.repository.AdminRepository;
import com.onlinepetconsultation.services.ProductService;

@Service
public class ProductServicesImp implements ProductService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ProductDao productDao;

	/*
	 * Performs save operation and returns Product created Response
	 */
	public ResponseEntity<ResponseStructure<Product>> saveProduct(ProductDto productDto, int adminId) {

		Product recievedProduct = null;
		Optional<Admin> user = adminRepository.findById(adminId);
		if (user.isPresent()&& productDto!=null) {
			Product product=new Product();
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setTotalCost(productDto.getTotalCost());
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

	/*
	 * Performs get operation and returns Product List fetched Response for Users
	 */
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProductByIsAvailable() {
		List<Product> itemList = productDao.getAllProducts(true);

		ResponseStructure<List<Product>> response = new ResponseStructure<List<Product>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(itemList);

		return new ResponseEntity<ResponseStructure<List<Product>>>(response, HttpStatus.OK);
	}

	/*
	 * Performs get operation and returns Product List fetched Response for Admin
	 */
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct() {
		List<Product> itemList = productDao.getAllProducts();

		ResponseStructure<List<Product>> response = new ResponseStructure<List<Product>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(itemList);

		return new ResponseEntity<ResponseStructure<List<Product>>>(response, HttpStatus.OK);
	}

	/*
	 * Performs update operation and returns the updated Product Response for Admin
	 */
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int adminId, ProductDto updateProduct,
			int productId) {
		Optional<Admin> admin = adminRepository.findById(adminId);
		Product product = productDao.getProductById(productId);
		Product recievedProduct = null;
		if (admin.isPresent()) {
			if (updateProduct != null && product != null) {
				if (updateProduct.getName() != null) {
					product.setName(updateProduct.getName());
				}
				if (updateProduct.getDescription() != null) {
					product.setDescription(updateProduct.getDescription());
				}
				if (updateProduct.getTotalCost() != 0) {
					product.setTotalCost(updateProduct.getTotalCost());
				}
				if (updateProduct.isAvailable() == true) {
					product.setAvailable(updateProduct.isAvailable());
				}
				recievedProduct = productDao.createProduct(product);
			}else {
				throw new ProductNotExistException();
			}
		} else {
			throw new AdminNotExistException();
		}
		ResponseStructure<Product> response = new ResponseStructure<Product>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(recievedProduct);

		return new ResponseEntity<ResponseStructure<Product>>(response, HttpStatus.OK);
	}

	/*
	 * Performs delete operations by setting field in false
	 */
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int adminId, int productId) {

		Optional<Admin> admin = adminRepository.findById(adminId);
		if (admin.isPresent()) {
			Product product = productDao.getProductById(productId);
			if (product != null) {
				product.setAvailable(false);
				productDao.createProduct(product);
			} else {
				throw new ProductNotExistException();
			}

		} else {
			throw new AdminNotExistException();
		}
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData("Item Removed Successfully");

		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);

	}

}
