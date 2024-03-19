package com.onlinepetconsultation.services;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Booking;

public interface BookingService {
	
	ResponseEntity<ResponseStructure<Booking>> bookingOrderConsultant(int userId, int consultantId);
	
	ResponseEntity<ResponseStructure<Booking>> searchBookingOrder(int bookingId);
	
	ResponseEntity<ResponseStructure<String>> deleteBookingOrder(int bookingId);

}