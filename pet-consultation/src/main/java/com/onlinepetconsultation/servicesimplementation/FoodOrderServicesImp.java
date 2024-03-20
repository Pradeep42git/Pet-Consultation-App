package com.onlinepetconsultation.servicesimplementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.onlinepetconsultation.exception.UserNotFoundException;
import com.onlinepetconsultation.repository.UserRepository;
import com.onlinepetconsultation.services.FoodOrderService;

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
	ProductDao dao;
	
	public ResponseEntity<?> saveFoodOrder(FoodOrderDto food,int id){
		Users user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User Found"));
		FoodOrder foodOrder=new FoodOrder();
		foodOrder.setCost(food.getCost());
		foodOrder.setOrderStatus(food.isOrderStatus());
		foodOrder.setFoodorderDateTime(LocalDateTime.now());
		List<Product> products=new ArrayList<Product>();
		for (Integer product : food.getProducts()) {
			Product product2=dao.getProductById(product);
			if(product2!=null) {
				products.add(product2);
			}
		}
		foodOrder.setProducts(products);
		foodOrderDao.saveFoodOrder(foodOrder);
		responseStructure.setData(foodOrder);
		responseStructure.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.FOUND);
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
