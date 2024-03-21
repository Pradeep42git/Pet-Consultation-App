package com.onlinepetconsultation.services;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.AdminDto;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.entity.Admin;

public interface AdminService {
	/*
	 * Performs save operation and returns Admin save Response
	 */
	ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminDto adminDto);
	/*
	 * Performs get operation based on id and returns admin fetched Response
	 */

	ResponseEntity<ResponseStructure<Admin>> getById(int adminId);

	/*
	 * Performs delete operations on admin based on id
	 */

	ResponseEntity<ResponseStructure<String>> deleteAdmin(int adminId);
	/*
	 * Performs get operation based on name and returns admin fetched Response
	 */

	ResponseEntity<ResponseStructure<Admin>> getByName(String adminName);

	/*
	 * Performs update operation and returns the updated admin based on
	 * name,email,password,phone
	 */
	ResponseEntity<ResponseStructure<Admin>> updateAdmin(AdminDto adminDto, int adminId);

}
