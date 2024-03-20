package com.onlinepetconsultation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onlinepetconsultation.entity.FoodOrder;
import com.onlinepetconsultation.servicesimplementation.FoodOrderServicesImp;
import com.onlinepetconsultation.dto.FoodDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.services.BookingService;

@RestController
@RequestMapping("/onlinepetconsultation/bs")
public class BookingServiceController {
	@Autowired
	private FoodOrderServicesImp foodOrderServices;
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("{user_id}/food")
	public ResponseEntity<?> saveFoodOrders(@RequestBody FoodDto dto,@PathVariable int user_id) throws Exception{
		return foodOrderServices.saveFoodOrder(dto, user_id);
		
	}
	
	@GetMapping("/search/{order_id}")
	public ResponseEntity<?> searchFoodOrder(@PathVariable int order_id){
		return foodOrderServices.searchFoodOrder(order_id);
	}
	
	@PutMapping("/update/{order_id}")
	public ResponseEntity<?> updateFoodOrder(@PathVariable int order_id, @RequestBody FoodOrder order){
		return foodOrderServices.updateFoodOrder(order_id, order);
	}
	
	@DeleteMapping("/delete/{order_id}")
	public ResponseEntity<?> deleteFoodOrder(@PathVariable int order_id){
		return foodOrderServices.deleteFoodOrder(order_id);
	}
	
		
	@PostMapping("/{userId}/{consultantId}")
	public ResponseEntity<ResponseStructure<Booking>> bookingOrderConsultant(@PathVariable int userId, @PathVariable int consultantId){
		return bookingService.bookingOrderConsultant(userId, consultantId);
	}
	
	@GetMapping("get/{bookingId}")
	public ResponseEntity<ResponseStructure<Booking>> searchBookingOrder(@PathVariable int bookingId){
		return bookingService.searchBookingOrder(bookingId);
	}
	
	@DeleteMapping("get/{bookingId}")
	public ResponseEntity<ResponseStructure<String>> deleteBookingOrder(@PathVariable int bookingId){
		return bookingService.deleteBookingOrder(bookingId);
	}

}
