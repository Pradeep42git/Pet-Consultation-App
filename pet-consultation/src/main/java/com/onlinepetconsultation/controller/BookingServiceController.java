package com.onlinepetconsultation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.onlinepetconsultation.dto.BookingResponse;
import com.onlinepetconsultation.dto.FoodOrderDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.FoodOrder;
import com.onlinepetconsultation.services.BookingService;
import com.onlinepetconsultation.servicesimplementation.FoodOrderServicesImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

/*
 * this controller class deals with CRUD operations related to consultant booking 
 * and Food ordering. 
 */

@RestController
@RequestMapping("/opc/bs")
public class BookingServiceController {
	@Autowired
	private FoodOrderServicesImp foodOrderServices;
	@Autowired
	private BookingService bookingService;

	
	//save the food order and generate a bill based on product cost and create a response object as FoodOrder
	//to a specific user
//	@ApiResponse(description = "To create Food Order",responseCode = "201", useReturnTypeSchema = true)
//	@Operation(summary = "create food Order", description = "Create Food Order by a user")
	@PostMapping("{user_id}/food")
	public ResponseEntity<?> saveFoodOrders(@Valid @RequestBody FoodOrderDto dto, BindingResult result,
			@PathVariable int user_id) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return foodOrderServices.saveFoodOrder(dto, user_id);
	}

	// save the food order and generate a bill based on product cost and create a
	// response object as FoodOrder
	// to a specific user
	@PostMapping("{user_id}/food")
	public ResponseEntity<?> saveFoodOrders(@RequestBody FoodOrderDto dto, @PathVariable int user_id) {
		return foodOrderServices.saveFoodOrder(dto, user_id);
	}

	// search the food Order based on FoodOrder Id and return the response object as
	// FoodOrder
	@GetMapping("/search/{order_id}")
	public ResponseEntity<?> searchFoodOrder(@PathVariable int order_id) {
		return foodOrderServices.searchFoodOrder(order_id);
	}

	// update the food order by getting their id and FoodOrder Object
	@PutMapping("/update/{order_id}")
	public ResponseEntity<?> updateFoodOrder(@PathVariable int order_id, @Valid @RequestBody FoodOrder order,
			BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return foodOrderServices.updateFoodOrder(order_id, order);
	}

	// delete the food order by giving user Id and order Id.
	// here, user id is taken to remove the Food order reference present in the
	// Users entity.
	@DeleteMapping("{user_id}/delete_food/{order_id}")
	public ResponseEntity<?> deleteFoodOrder(@PathVariable int order_id, @PathVariable int user_id) {
		return foodOrderServices.deleteFoodOrder(order_id, user_id);
	}

	@PostMapping("/{userId}/{consultantId}")
	public ResponseEntity<ResponseStructure<BookingResponse>> bookingOrderConsultant(@PathVariable int userId,
			@PathVariable int consultantId) {
		return bookingService.bookingOrderConsultant(userId, consultantId);
	}

	@GetMapping("get/{bookingId}")
	public ResponseEntity<ResponseStructure<BookingResponse>> searchBookingOrder(@PathVariable int bookingId) {
		return bookingService.searchBookingOrder(bookingId);
	}

	@DeleteMapping("delete_booking/{bookingId}")
	public ResponseEntity<ResponseStructure<String>> deleteBookingOrder(@PathVariable int bookingId) {
		return bookingService.deleteBookingOrder(bookingId);
	}

}
