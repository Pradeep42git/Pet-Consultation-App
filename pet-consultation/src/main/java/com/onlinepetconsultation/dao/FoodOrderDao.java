  package com.onlinepetconsultation.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.dto.FoodOrderDto;
import com.onlinepetconsultation.entity.FoodOrder;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.exception.FoodOrderNotFoundException;
import com.onlinepetconsultation.exception.ProductNotExistException;
import com.onlinepetconsultation.repository.FoodOrderRepository;
import com.onlinepetconsultation.repository.ProductRepository;

@Repository
public class FoodOrderDao {

	@Autowired
	private FoodOrderRepository foodOrderRepository;
	@Autowired
	private ProductRepository productRepository;
	

	public FoodOrder saveFoodOrder(FoodOrder food) {
		return foodOrderRepository.save(food);
	}
	

	public Optional<FoodOrder> searchFoodOrder(int id) {
		return foodOrderRepository.findById(id);
	}
	
	public FoodOrder updatefoodOrder(int id , FoodOrderDto order) {
		FoodOrder foodOrder = searchFoodOrder(id).orElseThrow(()-> new FoodOrderNotFoundException("No Order Found"));
		foodOrder.setFoodorderDateTime(LocalDateTime.now());
		foodOrder.setProducts(fetchProduct(order.getProducts()));
		return saveFoodOrder(foodOrder);
		
	}
	
	public String deleteFoodOrder(int id) {
		FoodOrder foodOrder = searchFoodOrder(id).orElseThrow(()-> new FoodOrderNotFoundException("No Order Found with ID "+id));
		foodOrder.setProducts(null);
		foodOrderRepository.delete(foodOrder);
		return "Order with ID "+id+"deleted Sucessfully";
		
	}
	private List<Product> fetchProduct(List<Integer> productId){
		List<Product> products= new ArrayList<Product>();
		for (Integer integer : productId) {
			Product product=productRepository.findById(integer).orElseThrow( ()-> new ProductNotExistException("Product with Id "+integer+" not found " ));
			products.add(product);
		}
		return products;
	}
	
}
