package com.onlinepetconsultation.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.entity.FoodOrder;
import com.onlinepetconsultation.exception.FoodOrderNotFoundException;
import com.onlinepetconsultation.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {

	@Autowired
	private FoodOrderRepository foodOrderRepository;
	
	public FoodOrder saveFoodOrder(FoodOrder food) {
		return foodOrderRepository.save(food);
	}
	
	public Optional<FoodOrder> searchFoodOrder(int id) {
		return foodOrderRepository.findById(id);
	}
	
	public FoodOrder updatefoodOrder(int id , FoodOrder order) {
		FoodOrder foodOrder = searchFoodOrder(id).orElseThrow(()-> new FoodOrderNotFoundException("No Order Found"));
		foodOrder.setCost(order.getCost()==0?foodOrder.getCost():order.getCost());
		foodOrder.setOrderStatus(order.isOrderStatus());
		foodOrder.setFoodorderDateTime(LocalDateTime.now());
		foodOrder.setProducts(order.getProducts()==null?foodOrder.getProducts():order.getProducts());
		return saveFoodOrder(foodOrder);
		
	}
	
	public String deleteFoodOrder(int id) {
		FoodOrder foodOrder = searchFoodOrder(id).orElseThrow(()-> new FoodOrderNotFoundException("No Order Found"));
		foodOrder.setProducts(null);
		foodOrderRepository.delete(foodOrder);
		return "Order deleted Sucessfully";
		
	}
	
}
