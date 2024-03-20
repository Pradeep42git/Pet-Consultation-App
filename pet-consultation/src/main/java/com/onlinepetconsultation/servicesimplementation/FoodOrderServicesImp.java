package com.onlinepetconsultation.servicesimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.FoodOrderDao;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.FoodOrder;
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
	
	public ResponseEntity<?> saveFoodOrder(FoodOrder food,int id){
		Users user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User Found"));
		FoodOrder  foodOrder= foodOrderDao.saveFoodOrder(food);
		List<FoodOrder> foodList1=(user.getFoodOrders().isEmpty()) ? new ArrayList<FoodOrder>():user.getFoodOrders() ;
		user.setFoodOrders(foodList1);
		if(foodOrder!=null) {
			responseStructure.setData(foodOrder);
			responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure,HttpStatus.CREATED);
		}
		else {
			responseStructure1.setData("Food Order Not Created Sucessfully");
			responseStructure1.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			responseStructure1.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure1,HttpStatus.BAD_REQUEST);
		}
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
