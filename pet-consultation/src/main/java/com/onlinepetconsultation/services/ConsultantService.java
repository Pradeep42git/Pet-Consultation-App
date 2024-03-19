package com.onlinepetconsultation.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.ConsultantDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.entity.Consultant;

public interface ConsultantService {

	ResponseEntity<ResponseStructure<Consultant>> saveConsultant(ConsultantDto consultantDto, int adminId);
	
	ResponseEntity<ResponseStructure<List<Consultant>>> getAllConsultants();
	
	ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantsByDesignation(String designation);
	
	ResponseEntity<ResponseStructure<Consultant>> updateConsultant(ConsultantDto consultantDto, int adminId,int consultantId);
	
	ResponseEntity<ResponseStructure<String>> removeConsultant(int consultantId, int adminId);
	
	ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(int consultantId, int adminId);
}
