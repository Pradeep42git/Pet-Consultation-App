package com.onlinepetconsultation.servicesimplementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.FoodOrderDao;
import com.onlinepetconsultation.dto.FoodDto;
import com.onlinepetconsultation.dto.Food_ProductDtoResponse;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.FoodOrder;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.entity.Users;
import com.onlinepetconsultation.exception.FoodOrderNotFoundException;
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
	ResponseStructure <List<Food_ProductDtoResponse>> responseStructure2;
	@Autowired
	Users users;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	

	
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
	
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodDto order,int id){
		Users user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User Found"));
		FoodOrder foodOrder= new FoodOrder();
		foodOrder.setCost(order.getCost());
		foodOrder.setFoodorderDateTime(LocalDateTime.now());
		foodOrder.setOrderStatus(order.isOrderStatus());
		List<Product> products= new ArrayList<Product>();
		for (String product_name : order.getProduct_name()) {
			Product product = productRepository.findByName(product_name);
			if(product!=null) {
				products.add(product);
			}
			else {
				throw new RuntimeException();
			}
			
		}
		
		List<FoodOrder> foodList1=(user.getFoodOrders().isEmpty()) ? new ArrayList<FoodOrder>():user.getFoodOrders() ;
		foodList1.add(foodOrder);
		user.setFoodOrders(foodList1);		
		FoodOrder foodOrder1 = foodOrderDao.saveFoodOrder(foodOrder);
		responseStructure.setData(foodOrder);
		responseStructure.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.FOUND);
	}

		
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
