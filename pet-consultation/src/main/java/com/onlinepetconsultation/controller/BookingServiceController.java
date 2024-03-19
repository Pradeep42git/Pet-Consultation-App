package com.onlinepetconsultation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.services.BookingService;

@RestController
@RequestMapping("/onlinepetconsultation/bs")
public class BookingServiceController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/{userId}/{consultantId}")
	public ResponseEntity<ResponseStructure<Booking>> bookingOrderConsultant(int userId, int consultantId){
		return bookingService.bookingOrderConsultant(userId, consultantId);
	}
	
	@GetMapping("get/{bookingId}")
	public ResponseEntity<ResponseStructure<Booking>> searchBookingOrder(int bookingId){
		return bookingService.searchBookingOrder(bookingId);
	}
	
	@DeleteMapping("get/{bookingId}")
	public ResponseEntity<ResponseStructure<String>> deleteBookingOrder(int bookingId){
		return bookingService.deleteBookingOrder(bookingId);
	}

}
