package com.onlinepetconsultation.services;

import org.springframework.http.ResponseEntity;


import com.onlinepetconsultation.dto.AdminDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Admin;

public interface AdminService {
	 ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminDto adminDto );
	 
	 ResponseEntity<ResponseStructure<Admin>> getById(int adminId);
	 
	 
	 ResponseEntity<ResponseStructure<Admin>> getByName(int adminName);
	 
	 ResponseEntity<ResponseStructure<String>> deleteAdmin(int adminId);

	

}
