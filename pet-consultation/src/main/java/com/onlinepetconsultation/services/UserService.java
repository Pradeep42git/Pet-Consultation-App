package com.onlinepetconsultation.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.dto.UsersDto;
import com.onlinepetconsultation.entity.Users;

public interface UserService {

	/*
	 * Performs save operation and returns user save Response
	 */
	ResponseEntity<ResponseStructure<Users>> saveUser(UsersDto usersDto);

	/*
	 * Performs get operation based on id and returns user fetched Response
	 */
	ResponseEntity<ResponseStructure<Users>> getUserById(int userId);

	/*
	 * Performs get operation based on name and returns user fetched Response
	 */

	ResponseEntity<ResponseStructure<Users>> getUserByName(String userName);

	/*
	 * Performs delete operations on user based on id
	 */

	ResponseEntity<ResponseStructure<String>> deleteUser(int userId);

	/*
	 * Performs update operation and returns the updated user based on
	 * name,email,password,phone,address
	 */
	ResponseEntity<ResponseStructure<Users>> updateUser(UsersDto usersDto, int UserId);

	
	/*
	 * Performs get operations on user and returns list of users
	 */
	ResponseEntity<ResponseStructure<List<Users>>> getAllUsers();
}
