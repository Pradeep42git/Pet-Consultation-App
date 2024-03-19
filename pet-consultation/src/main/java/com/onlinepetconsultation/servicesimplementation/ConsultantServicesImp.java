package com.onlinepetconsultation.servicesimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.ConsultantDao;
import com.onlinepetconsultation.dto.ConsultantDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.exception.AdminNotExistException;
import com.onlinepetconsultation.repository.AdminRepository;
import com.onlinepetconsultation.services.ConsultantService;
import com.onlinepetconsultation.util.ConsultantRoles;

@Service
public class ConsultantServicesImp implements ConsultantService {

	@Autowired
	private ConsultantDao consultantDao;
	
	@Autowired
	private AdminRepository adminRepository;
	
	public ResponseEntity<ResponseStructure<Consultant>> saveConsultant(ConsultantDto consultantDto,int adminId) {
		Optional<Admin> optional=adminRepository.findById(adminId);
		Consultant receivedConsultant=null;
		if( optional.isPresent() && consultantDto!=null) {
			Consultant consultant=new Consultant();
			consultant.setName(consultantDto.getName());
			consultant.setEmail(consultantDto.getEmail());
			consultant.setAddress(consultantDto.getAddress());
			if(consultantDto.getDesignation().equalsIgnoreCase("DENTIST")) {
				consultant.setDesignation(ConsultantRoles.DENTIST);
			}
			if(consultantDto.getDesignation().equalsIgnoreCase("GENERAL")) {
				consultant.setDesignation(ConsultantRoles.GENERAL);
			}
			if(consultantDto.getDesignation().equalsIgnoreCase("NEUROLOGIST")) {
				consultant.setDesignation(ConsultantRoles.NEUROLOGIST);
			}
			if(consultantDto.getDesignation().equalsIgnoreCase("NUTRITIONIST")) {
				consultant.setDesignation(ConsultantRoles.NUTRITIONIST);
			}
			if(consultantDto.getDesignation().equalsIgnoreCase("OPHTHALMOLOGIST")) {
				consultant.setDesignation(ConsultantRoles.OPHTHALMOLOGIST);
			}
			if(consultantDto.getDesignation().equalsIgnoreCase("RADIOLOGIST")) {
				consultant.setDesignation(ConsultantRoles.RADIOLOGIST);
			}
			if(consultantDto.getDesignation().equalsIgnoreCase("SURGEON")) {
				consultant.setDesignation(ConsultantRoles.SURGEON);
			}
			receivedConsultant=consultantDao.createConsultant(consultant);
		}else {
			throw new AdminNotExistException();
		}
		ResponseStructure<Consultant> response = new ResponseStructure<Consultant>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(receivedConsultant);

		return new ResponseEntity<ResponseStructure<Consultant>>(response, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Consultant>>> getAllConsultants(){
		
		List<Consultant> consultant=consultantDao.getAllConsultant();
		ResponseStructure<List<Consultant>> response = new ResponseStructure<List<Consultant>>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(consultant);
		return new ResponseEntity<ResponseStructure<List<Consultant>>>(response, HttpStatus.CREATED);
	}
}
