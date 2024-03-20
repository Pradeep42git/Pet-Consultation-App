package com.onlinepetconsultation.servicesimplementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.FoodOrderDao;
import com.onlinepetconsultation.dao.ProductDao;
import com.onlinepetconsultation.dto.FoodOrderDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.FoodOrder;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.entity.Users;
import com.onlinepetconsultation.exception.FoodOrderNotFoundException;
import com.onlinepetconsultation.exception.ProductNotExistException;
import com.onlinepetconsultation.exception.UserNotFoundException;
import com.onlinepetconsultation.repository.ProductRepository;
import com.onlinepetconsultation.repository.UserRepository;
import com.onlinepetconsultation.services.FoodOrderService;

import jakarta.transaction.Transactional;

@Service
public class FoodOrderServicesImp implements FoodOrderService {
	@Autowired
	FoodOrderDao foodOrderDao;
	@Autowired
	ResponseStructure<FoodOrder>responseStructure ;
	@Autowired
	ResponseStructure<String>responseStructure1 ;
	@Autowired
	Users users;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductDao dao;
	
	public ResponseEntity<?> saveFoodOrder(FoodOrderDto food,int id){
		Users user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User Found"));
		FoodOrder foodOrder=new FoodOrder();
		foodOrder.setOrderStatus(food.isOrderStatus());
		foodOrder.setFoodorderDateTime(LocalDateTime.now());
		double foodBill=0; 
		List<Product> products=new ArrayList<Product>();
		for (Integer product : food.getProducts()) {
			Product product2=dao.getProductById(product);
			if(product2!=null) {
				foodBill=product2.getTotalCost()+foodBill;
				products.add(product2);
			}else {
				throw new ProductNotExistException("Product not found");
			}
		}
		foodOrder.setCost(foodBill);
		foodOrder.setProducts(products);
		foodOrderDao.saveFoodOrder(foodOrder);
		responseStructure.setData(foodOrder);
		responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> searchFoodOrder(int id){
		FoodOrder order = foodOrderDao.searchFoodOrder(id).orElseThrow(()->new FoodOrderNotFoundException("Order Not Found"));
		responseStructure.setData(order);
		responseStructure.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.FOUND);
	}
	
	public ResponseEntity<?> updateFoodOrder(int id,FoodOrder order){
		responseStructure.setData(foodOrderDao.updatefoodOrder(id, order));
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure,HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> deleteFoodOrder(int id){
		responseStructure1.setData(foodOrderDao.deleteFoodOrder(id));
		responseStructure1.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure1.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure1,HttpStatus.OK);
	}
	
		
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
