package com.onlinepetconsultation.services;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.FoodOrderDto;
import com.onlinepetconsultation.entity.FoodOrder;

public interface FoodOrderService {
	
	// save the food order and generate a bill based on product cost and create a response object as FoodOrder
	public ResponseEntity<?> saveFoodOrder(FoodOrderDto food, int id);
	
	// search the food Order based on FoodOrder Id and return the response object as FoodOrder
	public ResponseEntity<?> searchFoodOrder(int id);
	
	// update the food order by getting their id and FoodOrder Object
	public ResponseEntity<?> updateFoodOrder(int id,FoodOrder order);
	
	// delete the food order by giving user Id and order Id.
	// here, user id is taken to remove the Food order reference present in the Users entity.
	ResponseEntity<?> deleteFoodOrder(int order_id, int user_id);
}
