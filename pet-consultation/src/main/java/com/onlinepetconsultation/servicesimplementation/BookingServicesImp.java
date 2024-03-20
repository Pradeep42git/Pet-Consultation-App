package com.onlinepetconsultation.servicesimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.BookingDao;
import com.onlinepetconsultation.dto.BookingResponse;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.services.BookingService;

@Service
public class BookingServicesImp implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	public ResponseEntity<ResponseStructure<BookingResponse>> bookingOrderConsultant(int userId, int consultantId) {

		Booking booking = bookingDao.bookingOrderConsultant(userId, consultantId);

		ResponseStructure<BookingResponse> responseStructure = new ResponseStructure<BookingResponse>();
		
		BookingResponse bookingResponse = BookingResponse
				.builder()
				.Id(booking.getId())
				.bookingDateTime(booking.getBookingDateTime())
				.user(booking.getUser())
				.consultant(booking.getConsultant())
				.build();

		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructure.setData(bookingResponse);

		return new ResponseEntity<ResponseStructure<BookingResponse>>(responseStructure, HttpStatus.CREATED);
		
	}

	public ResponseEntity<ResponseStructure<BookingResponse>> searchBookingOrder(int bookingId) {

		Booking booking = bookingDao.searchBookingOrder(bookingId);

		ResponseStructure<BookingResponse> responseStructure = new ResponseStructure<BookingResponse>();
		
		BookingResponse bookingResponse = BookingResponse
				.builder()
				.Id(booking.getId())
				.bookingDateTime(booking.getBookingDateTime())
				.user(booking.getUser())
				.consultant(booking.getConsultant())
				.build();

		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructure.setData(bookingResponse);

		return new ResponseEntity<ResponseStructure<BookingResponse>>(responseStructure, HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<String>> deleteBookingOrder(int bookingId) {

		String del_status = bookingDao.deleteBookingOrder(bookingId);

		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setData(del_status);

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

	}

}
