package com.onlinepetconsultation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
