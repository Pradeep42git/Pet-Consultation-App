package com.onlinepetconsultation.controller;

import java.util.List;

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

import com.onlinepetconsultation.dto.ConsultantDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.services.ConsultantService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/onlinepetconsultantion/consultants")
public class ConsultantController {

	@Autowired
	private ConsultantService consultantService;

	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<Consultant>> saveConsultant(@Valid @RequestBody ConsultantDto consultantDto,
			BindingResult result) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return consultantService.saveConsultant(consultantDto);
	}

	@GetMapping("/admins/consultants")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantForAdmin() {
		return consultantService.getAllConsultantsForAdmin();
	}

	@PutMapping("/admins/{consultantId}")
	public ResponseEntity<ResponseStructure<Consultant>> updateConsultant(
			@Valid @RequestBody ConsultantDto consultantDto, BindingResult result, @PathVariable int consultantId) {

		if (result.hasErrors()) {
			String message = "";
			for (FieldError err : result.getFieldErrors()) {
				message += err.getDefaultMessage();
			}
			throw new ValidationException(message);
		}

		return consultantService.updateConsultant(consultantDto, consultantId);
	}

	@GetMapping("/admins/getAllBookings/{consultantId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(@PathVariable int consultantId) {
		return consultantService.getAllBookings(consultantId);
	}

	@DeleteMapping("/admins/{consultantId}")
	public ResponseEntity<ResponseStructure<String>> removeConsultant(@PathVariable int consultantId) {
		return consultantService.removeConsultant(consultantId);
	}

	@GetMapping("/users/getConsultantDesignation/{designation}")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantByDesignation(
			@PathVariable	 String designation) {
		return consultantService.getConsultantsByDesignation(designation);
	}

	@GetMapping("/users/getAllConsultant")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultant() {
		return consultantService.getAllConsultantsForUsers();
	}
}
