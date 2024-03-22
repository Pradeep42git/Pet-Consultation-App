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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/onlinepetconsultantion/consultants")
public class ConsultantController {

	@Autowired
	private ConsultantService consultantService;

	@ApiResponse(description = "Consultant created",responseCode = "201" )
	@Operation(summary = "To save a consultant", description = "To save a consultant")
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

	@ApiResponse(description = "Consultant List",responseCode = "200" )
	@Operation(summary = "To see all consultants", description = "all consultants from Database will be fetched as List")
	@GetMapping("/admins/consultants")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantForAdmin() {
		return consultantService.getAllConsultantsForAdmin();
	}
	
	@ApiResponse(description = "Consultant updated",responseCode = "200" )
	@Operation(summary = "To update consultants by ID", description = "Update consultants based on their ID")
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
	
	@ApiResponse(description = "Booking order for a consultant",responseCode = "200" )
	@Operation(summary = "To see all bookings for a consultant", description = "To see all bookings for a consultant by giving their ID")
	@GetMapping("/admins/getAllBookings/{consultantId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(@PathVariable int consultantId) {
		return consultantService.getAllBookings(consultantId);
	}
	
	@ApiResponse(description = "Consultant removed",responseCode = "200" )
	@Operation(summary = "To remove a consultant by ID", description = "To remove a consultant by giving their ID")
	@DeleteMapping("/admins/{consultantId}")
	public ResponseEntity<ResponseStructure<String>> removeConsultant(@PathVariable int consultantId) {
		return consultantService.removeConsultant(consultantId);
	}


	@ApiResponse(description = "Consultant List",responseCode = "200" )
	@Operation(summary = "To see all consultants by Designation", description = "To fetch a list of all consultants by their Designation")
	@GetMapping("/users/getConsultantDesignation/{designation}")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantByDesignation(
			@PathVariable	 String designation) {
		return consultantService.getConsultantsByDesignation(designation);
	}

	@ApiResponse(description = "Consultant List",responseCode = "200" )
	@Operation(summary = "To see only the available consultants", description = "To fetch all available consultants in DataBase")
	@GetMapping("/users/getAllConsultant")
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultant() {
		return consultantService.getAllConsultantsForUsers();
	}
}
