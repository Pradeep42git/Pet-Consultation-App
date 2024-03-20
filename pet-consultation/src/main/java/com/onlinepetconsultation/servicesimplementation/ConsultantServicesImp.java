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
import com.onlinepetconsultation.entity.Booking;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.exception.AdminNotExistException;
import com.onlinepetconsultation.exception.ConsultantNotFoundException;
import com.onlinepetconsultation.repository.AdminRepository;
import com.onlinepetconsultation.services.ConsultantService;
import com.onlinepetconsultation.util.ConsultantRoles;

@Service
public class ConsultantServicesImp implements ConsultantService {

	@Autowired
	private ConsultantDao consultantDao;

	@Autowired
	private AdminRepository adminRepository;

	/*
	 * Performs save operation and returns Consultant created Response
	 */
	public ResponseEntity<ResponseStructure<Consultant>> saveConsultant(ConsultantDto consultantDto, int adminId) {
		Optional<Admin> optional = adminRepository.findById(adminId);
		Consultant receivedConsultant = null;
		if (optional.isPresent() && consultantDto != null) {
			Consultant consultant = new Consultant();
			consultant.setName(consultantDto.getName());
			consultant.setEmail(consultantDto.getEmail());
			consultant.setAddress(consultantDto.getAddress());
			if (consultantDto.getDesignation().equalsIgnoreCase("DENTIST")) {
				consultant.setDesignation(ConsultantRoles.DENTIST);
			}
			if (consultantDto.getDesignation().equalsIgnoreCase("GENERAL")) {
				consultant.setDesignation(ConsultantRoles.GENERAL);
			}
			if (consultantDto.getDesignation().equalsIgnoreCase("NEUROLOGIST")) {
				consultant.setDesignation(ConsultantRoles.NEUROLOGIST);
			}
			if (consultantDto.getDesignation().equalsIgnoreCase("NUTRITIONIST")) {
				consultant.setDesignation(ConsultantRoles.NUTRITIONIST);
			}
			if (consultantDto.getDesignation().equalsIgnoreCase("OPHTHALMOLOGIST")) {
				consultant.setDesignation(ConsultantRoles.OPHTHALMOLOGIST);
			}
			if (consultantDto.getDesignation().equalsIgnoreCase("RADIOLOGIST")) {
				consultant.setDesignation(ConsultantRoles.RADIOLOGIST);
			}
			if (consultantDto.getDesignation().equalsIgnoreCase("SURGEON")) {
				consultant.setDesignation(ConsultantRoles.SURGEON);
			}
			consultant.setAvailable(true);
			receivedConsultant = consultantDao.createConsultant(consultant);
		} else {
			throw new AdminNotExistException();
		}
		ResponseStructure<Consultant> response = new ResponseStructure<Consultant>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(receivedConsultant);

		return new ResponseEntity<ResponseStructure<Consultant>>(response, HttpStatus.CREATED);
	}
	
	/*
	 * perform the retrive list of consultant operation for the Admin
	 */
	public ResponseEntity<ResponseStructure<List<Consultant>>> getAllConsultantsForAdmin() {

		List<Consultant> consultant = consultantDao.getAllConsultantForAdmin();
		ResponseStructure<List<Consultant>> response = new ResponseStructure<List<Consultant>>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(consultant);
		return new ResponseEntity<ResponseStructure<List<Consultant>>>(response, HttpStatus.CREATED);
	}

	/*
	 * perform the retrive list of consultant operation for the users
	 */
	public ResponseEntity<ResponseStructure<List<Consultant>>> getAllConsultantsForUsers() {

		List<Consultant> consultant = consultantDao.getAllConsultantForUsers();
		ResponseStructure<List<Consultant>> response = new ResponseStructure<List<Consultant>>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Success");
		response.setData(consultant);
		return new ResponseEntity<ResponseStructure<List<Consultant>>>(response, HttpStatus.CREATED);
	}

	/*
	 * perform the retrive list of consultant opertion by giving the designation
	 */
	public ResponseEntity<ResponseStructure<List<Consultant>>> getConsultantsByDesignation(
			String designation) {
		List<Consultant> con_list = null;
		if (designation.equalsIgnoreCase("DENTIST")) {
			con_list = consultantDao.getConsultantsByDesignation(ConsultantRoles.DENTIST);
		}
		if (designation.equalsIgnoreCase("GENERAL")) {
			con_list = consultantDao.getConsultantsByDesignation(ConsultantRoles.GENERAL);
		}
		if (designation.equalsIgnoreCase("NEUROLOGIST")) {
			con_list = consultantDao.getConsultantsByDesignation(ConsultantRoles.NEUROLOGIST);
		}
		if (designation.equalsIgnoreCase("NUTRITIONIST")) {
			con_list = consultantDao.getConsultantsByDesignation(ConsultantRoles.NUTRITIONIST);
		}
		if (designation.equalsIgnoreCase("OPHTHALMOLOGIST")) {
			con_list = consultantDao.getConsultantsByDesignation(ConsultantRoles.OPHTHALMOLOGIST);
		}
		if (designation.equalsIgnoreCase("RADIOLOGIST")) {
			con_list = consultantDao.getConsultantsByDesignation(ConsultantRoles.RADIOLOGIST);
		}
		if (designation.equalsIgnoreCase("SURGEON")) {
			con_list = consultantDao.getConsultantsByDesignation(ConsultantRoles.SURGEON);
		}
		ResponseStructure<List<Consultant>> responseStructure = new ResponseStructure<List<Consultant>>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setData(con_list);

		return new ResponseEntity<ResponseStructure<List<Consultant>>>(responseStructure, HttpStatus.OK);
	}

	/*
	 * perform the update operation for the consultant by using consultantId
	 */
	public ResponseEntity<ResponseStructure<Consultant>> updateConsultant(ConsultantDto consultantDto, int adminId,
			int consultantId) {

		Consultant receivedConsultant = null;
		if (adminRepository.existsById(adminId) && consultantDto != null) {
			Consultant consultant = consultantDao.getConsultantById(consultantId);
			if (consultant != null) {
				if (consultantDto.getName() != null) {
					consultant.setName(consultantDto.getName());
				}
				if (consultantDto.getAddress() != null) {
					consultant.setAddress(consultantDto.getAddress());
				}
				if (consultantDto.getEmail() != null) {
					consultant.setEmail(consultantDto.getEmail());
				}
				consultant.setAvailable(true);
				receivedConsultant = consultantDao.createConsultant(consultant);
			} else {
				throw new ConsultantNotFoundException();
			}
		} else {
			throw new AdminNotExistException();
		}

		ResponseStructure<Consultant> response = new ResponseStructure<Consultant>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Updated Successfully");
		response.setData(receivedConsultant);

		return new ResponseEntity<ResponseStructure<Consultant>>(response, HttpStatus.OK);
	}

	/*
	 * perform the get bookings of any consultant by consultant Id
	 */
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBookings(int consultantId, int adminId) {
		List<Booking> bookings=null;
		if (adminRepository.existsById(adminId)) {
			if (consultantId != 0) {
				Consultant consultant = consultantDao.getConsultantById(consultantId);
				if (consultant != null) {
					bookings=consultant.getBooking();
				}
			}else {
				throw new ConsultantNotFoundException();
			}
		}else {
			throw new AdminNotExistException();
		}
		
		ResponseStructure<List<Booking>>response=new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("List of Bookings");
		response.setData(bookings);
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response,HttpStatus.OK);

	}

	/*
	 * Performs delete operations by setting field in false
	 */
	public ResponseEntity<ResponseStructure<String>> removeConsultant(int consultantId, int adminId) {
		if (adminRepository.existsById(adminId)) {
			if (consultantId != 0) {
				Consultant consultant = consultantDao.getConsultantById(consultantId);
				if (consultant != null) {
					consultant.setAvailable(false);
					consultantDao.createConsultant(consultant);
					ResponseStructure<String> responseStructure = new ResponseStructure<String>();

					responseStructure.setStatusCode(HttpStatus.OK.value());
					responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
					responseStructure.setData("Consultant removed");

					return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
				} else {
					throw new ConsultantNotFoundException();
				}
			}
		} else {
			throw new AdminNotExistException();
		}
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();

		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setData("Consultant not removed");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

}
