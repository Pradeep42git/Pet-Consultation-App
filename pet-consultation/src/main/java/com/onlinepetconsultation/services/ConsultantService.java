package com.onlinepetconsultation.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.ConsultantDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.entity.Consultant;

public interface ConsultantService {

	/*
	 * Performs save operation and returns Consultant created Response
	 */
	ResponseEntity<ResponseStructure<Consultant>> saveConsultant(ConsultantDto consultantDto);

	/*
	 * perform the retrive list of consultant operation for the Admin
	 */
	ResponseEntity<ResponseStructure<List<Consultant>>> getAllConsultantsForAdmin();

	/*
	 * perform the retrive list of consultant operation for the users
	 */
	ResponseEntity<ResponseStructure<List<Consultant>>> getAllConsultantsForUsers();

	/*
	 * perform the retrive list of consultant opertion by giving the designation
	 */
	ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantsByDesignation(String designation);

	/*
	 * perform the update operation for the consultant by using consultantId
	 */
	ResponseEntity<ResponseStructure<Consultant>> updateConsultant(ConsultantDto consultantDto, int consultantId);

	/*
	 * Performs delete operations by setting field in false
	 */
	ResponseEntity<ResponseStructure<String>> removeConsultant(int consultantId);

	/*
	 * perform the get bookings of any consultant by consultant Id
	 */
	ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(int consultantId);
}
