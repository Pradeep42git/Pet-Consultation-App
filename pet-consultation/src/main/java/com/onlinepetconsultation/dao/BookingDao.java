package com.onlinepetconsultation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.entity.Users;
import com.onlinepetconsultation.exception.BookingNotFoundException;
import com.onlinepetconsultation.exception.ConsultantNotFoundException;
import com.onlinepetconsultation.exception.UsersNotFoundException;
import com.onlinepetconsultation.repository.BookingRepository;
import com.onlinepetconsultation.repository.ConsultantRepository;
import com.onlinepetconsultation.repository.UserRepository;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ConsultantRepository consultantRepository;

	@Autowired
	private UserRepository userRepository;

	// creating booking order for consultant
	public Booking bookingOrderConsultant(int userId, int consultantId) {

		Users user = userRepository.findById(userId)
				.orElseThrow(() -> new UsersNotFoundException("User with the ID " + userId + " is not found"));

		Consultant consultant = consultantRepository.findById(consultantId).orElseThrow(
				() -> new ConsultantNotFoundException("Consultant with the ID " + consultantId + " is not found"));

		Booking booking = new Booking();

		booking.setUser(user);
		booking.setConsultant(consultant);

		return bookingRepository.save(booking);
	}

	// search booking order
	public Booking searchBookingOrder(int bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow(
				() -> new BookingNotFoundException("Consultant booking with ID " + bookingId + " is not found"));
	}

	// delete booking order
	public String deleteBookingOrder(int bookingId) {
		Booking booking = searchBookingOrder(bookingId);
		bookingRepository.delete(booking);

		return "Booking with ID " + booking.getId() + " has been deleted";

	}
}
