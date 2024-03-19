package com.onlinepetconsultation.servicesimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.ConsultantDao;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.services.ConsultantService;
import com.onlinepetconsultation.util.ConsultantRoles;

@Service
public class ConsultantServicesImp implements ConsultantService {

	@Autowired
	private ConsultantDao consultantDao;

	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantsByDesignation(
			ConsultantRoles designation) {

		ResponseStructure<List<Consultant>> responseStructure = new ResponseStructure<List<Consultant>>();

		List<Consultant> con_list = consultantDao.getConsultantsByDesignation(designation);

		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setData(con_list);

		return new ResponseEntity<ResponseStructure<List<Consultant>>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> removeConsultant(int consultantId) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		consultantDao.removeConsultant(consultantId);

		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setData("Consultant removed");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

}
