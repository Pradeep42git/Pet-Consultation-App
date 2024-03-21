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

@Service
public class FoodOrderServicesImp implements FoodOrderService {
	@Autowired
	FoodOrderDao foodOrderDao;
	@Autowired
	ResponseStructure<FoodOrder> responseStructure;
	@Autowired
	ResponseStructure<String> responseStructure1;
	@Autowired
	Users users;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductDao dao;

	// save the food order and generate a bill based on product cost and create a
	// response object as FoodOrder
	// to a specific user
	public ResponseEntity<?> saveFoodOrder(FoodOrderDto food, int id) {
		Users user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No User Found with ID " + id));
		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setOrderStatus(food.isOrderStatus());
		foodOrder.setFoodorderDateTime(LocalDateTime.now());
		double foodBill = 0;
		List<Product> products = new ArrayList<Product>();
		for (Integer product : food.getProducts()) {
			Product product2 = dao.getProductById(product);
			if (product2 != null) {
				foodBill = product2.getTotalCost() + foodBill;
				products.add(product2);
			} else {
				throw new ProductNotExistException("Product with ID " + product + " not found");
			}
		}
		List<FoodOrder> foodList = (user.getFoodOrders().isEmpty() || user.getFoodOrders() == null)
				? new ArrayList<FoodOrder>()
				: user.getFoodOrders();
		foodOrder.setCost(foodBill);
		foodOrder.setProducts(products);
		foodList.add(foodOrder);
		user.setFoodOrders(foodList);
		foodOrderDao.saveFoodOrder(foodOrder);
		responseStructure.setData(foodOrder);
		responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.CREATED);
	}

	// search the food Order based on FoodOrder Id and return the response object as
	// FoodOrder
	public ResponseEntity<?> searchFoodOrder(int id) {
		FoodOrder order = foodOrderDao.searchFoodOrder(id)
				.orElseThrow(() -> new FoodOrderNotFoundException("Order Not Found"));
		responseStructure.setData(order);
		responseStructure.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.FOUND);
	}

	// update the food order by getting their id and FoodOrder Object
	public ResponseEntity<?> updateFoodOrder(int id, FoodOrder order) {
		responseStructure.setData(foodOrderDao.updatefoodOrder(id, order));
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);

	}

	// delete the food order by giving user Id and order Id.
//	here, user id is taken to remove the Food order reference present in the Users entity.
	public ResponseEntity<?> deleteFoodOrder(int order_id, int user_id) {
		Users user = userRepository.findById(user_id)
				.orElseThrow(() -> new UserNotFoundException("User with ID " + user_id + " is not found"));
		List<FoodOrder> fos = user.getFoodOrders();
		FoodOrder fo = null;
		for (FoodOrder food : fos) {
			if (food.getId() == order_id) {
				fo = food;
			} else {
				throw new FoodOrderNotFoundException("Food Order with ID " + order_id + " is not found");
			}
		}
		fos.remove(fo);
		user.setFoodOrders(fos);
		userRepository.save(user);
		responseStructure1.setData(foodOrderDao.deleteFoodOrder(fo.getId()));
		responseStructure1.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure1.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure1, HttpStatus.OK);
	}

}
