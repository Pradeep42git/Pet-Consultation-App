package com.onlinepetconsultation.services;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.BookingResponse;
import com.onlinepetconsultation.dto.ResponseStructure;

public interface BookingService {

	ResponseEntity<ResponseStructure<BookingResponse>> bookingOrderConsultant(int userId, int consultantId);

	ResponseEntity<ResponseStructure<BookingResponse>> searchBookingOrder(int bookingId);

	ResponseEntity<ResponseStructure<String>> deleteBookingOrder(int bookingId);

}