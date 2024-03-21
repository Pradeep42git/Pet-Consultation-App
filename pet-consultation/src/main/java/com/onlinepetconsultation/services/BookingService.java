package com.onlinepetconsultation.services;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.BookingResponse;
import com.onlinepetconsultation.dto.ResponseStructure;

public interface BookingService {

	//used to create a booking order and returns Booking response
	ResponseEntity<ResponseStructure<BookingResponse>> bookingOrderConsultant(int userId, int consultantId);

	//used to search a booking order and returns Booking response
	ResponseEntity<ResponseStructure<BookingResponse>> searchBookingOrder(int bookingId);

	//used to delete a booking order and returns String response
	ResponseEntity<ResponseStructure<String>> deleteBookingOrder(int bookingId);

}