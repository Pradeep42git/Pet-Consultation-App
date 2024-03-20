package com.onlinepetconsultation.services;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.entity.FoodOrder;

public interface FoodOrderService {
	
//	public ResponseEntity<?> saveFoodOrder(FoodOrder food,int id);
	public ResponseEntity<?> searchFoodOrder(int id);
	public ResponseEntity<?> updateFoodOrder(int id,FoodOrder order);
	ResponseEntity<?> deleteFoodOrder(int order_id, int user_id);
}
