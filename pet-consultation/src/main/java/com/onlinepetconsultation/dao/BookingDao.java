package com.onlinepetconsultation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.repository.BookingRepository;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepository bookingRepository;
}
