package com.onlinepetconsultation.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

/*
 * this controller class deals with CRUD operations related to consultant booking 
 * and Food ordering. 
 */



@RequestMapping("/onlinepetconsultantion/bookingservices")
@RestController
public class BookingServiceController {
	@Autowired
	private FoodOrderServicesImp foodOrderServices;
	@Autowired
	private BookingService bookingService;

	
	//save the food order and generate a bill based on product cost and create a response object as FoodOrder
	//to a specific user
	@ApiResponse(description = "Food order placed ",responseCode = "201" )
	@Operation(summary = "To place a food order by user", description = "Create food order by giving product ID")
	@PostMapping("{userId}/saveFoodOrder")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrders(@Valid @RequestBody FoodOrderDto dto, BindingResult result,
			@PathVariable int userId) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return foodOrderServices.saveFoodOrder(dto, userId);
	}

	
	// search the food Order based on FoodOrder Id and return the response object as
	// FoodOrder
	@ApiResponse(description = "FoodOrder found",responseCode = "200" )
	@Operation(summary = "To find a food order ", description = "Find a food order by giving order ID")
	@GetMapping("/search/{orderId}")
	public ResponseEntity<ResponseStructure<FoodOrder>> searchFoodOrder(@PathVariable int orderId) {
		return foodOrderServices.searchFoodOrder(orderId);
	}

	// update the food order by getting their id and FoodOrder Object
	@ApiResponse(description = "Food order updated",responseCode = "200" )
	@Operation(summary = "To update a food order ", description = "Update a food order by giving order ID")
	@PutMapping("/update/{orderId}")
	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(@PathVariable int orderId, @Valid @RequestBody FoodOrder order,
			BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return foodOrderServices.updateFoodOrder(orderId, order);
	}

	// delete the food order by giving user Id and order Id.
	// here, user id is taken to remove the Food order reference present in the
	// Users entity.
	@ApiResponse(description = "Food order deleted",responseCode = "200" )
	@Operation(summary = "To delete a food order ", description = "Delete a food order by giving user ID and order ID")
	@DeleteMapping("{userId}/delete_food/{orderId}")
	public ResponseEntity<ResponseStructure<String>> deleteFoodOrder(@PathVariable int orderId, @PathVariable int userId) {
		return foodOrderServices.deleteFoodOrder(orderId, userId);
	}
	
	@ApiResponse(description = "Consulatant booked",responseCode = "201" )
	@Operation(summary = "To book a consultant ", description = "Book a consultant by a specific user")
	@PostMapping("/{userId}/{consultantId}")
	public ResponseEntity<ResponseStructure<BookingResponse>> bookingOrderConsultant(@PathVariable int userId,
			@PathVariable int consultantId) {
		return bookingService.bookingOrderConsultant(userId, consultantId);
	}

	@ApiResponse(description = "Consultant booking order found",responseCode = "302" )
	@Operation(summary = "To find a consultant booking order ", description = "Find the consultant booked order by order ID")
	@GetMapping("get/{bookingId}")
	public ResponseEntity<ResponseStructure<BookingResponse>> searchBookingOrder(@PathVariable int bookingId) {
		return bookingService.searchBookingOrder(bookingId);
	}
	@ApiResponse(description = "Consultant booking order deleted",responseCode = "200" )
	@Operation(summary = "To delete a consultant booking order", description = "Delete a consulatant booking order by giving order ID")
	@DeleteMapping("delete_booking/{bookingId}")
	public ResponseEntity<ResponseStructure<String>> deleteBookingOrder(@PathVariable int bookingId) {
		return bookingService.deleteBookingOrder(bookingId);
	}

}
