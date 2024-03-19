package com.onlinepetconsultation.servicesimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.AdminDao;
import com.onlinepetconsultation.dto.AdminDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.services.AdminService;

@Service
public class AdminServicesImp implements AdminService {

	@Autowired
	private AdminDto adminDto;
	

	@Autowired
	private AdminDao adminDao;

	@Override
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminDto adminDto) {
		Admin admin = new Admin();
		admin.setName(adminDto.getName());
		admin.setEmail(adminDto.getEmail());
		admin.setPassword(adminDto.getPassword());
		admin.setPhone(adminDto.getPhone());
		Admin createdAdmin = adminDao.createAdmin(admin);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(" Admin saved succesfully");
		responseStructure.setData(createdAdmin);
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseStructure<Admin>> getById(int adminId) {
		Admin admin = adminDao.getAdminById(adminId);
		if (admin != null) {
			ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(" admin found");
			responseStructure.setData(admin);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
		} else {
			throw null;
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> getByName(int adminName) {
		Admin admin = adminDao.getAdminById(adminName);
		if (admin != null) {
			ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(" admin found");
			responseStructure.setData(admin);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
		} else {
			throw null;
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteAdmin(int adminId) {
		boolean admin = adminDao.deleteAdmin(adminId);
		if (admin) {
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("admin deleted");
			responseStructure.setData("admin removed successfully");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		}
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("admin not deleted");
		responseStructure.setData("admin not removed");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	
	

	

	
	}

