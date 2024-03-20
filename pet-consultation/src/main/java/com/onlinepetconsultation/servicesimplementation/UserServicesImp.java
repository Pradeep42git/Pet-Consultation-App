package com.onlinepetconsultation.servicesimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.UserDao;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.dto.UsersDto;
import com.onlinepetconsultation.entity.Users;
import com.onlinepetconsultation.exception.UsersNotFoundException;
import com.onlinepetconsultation.services.UserService;

@Service
public class UserServicesImp implements UserService {

	@Autowired
	private UserDao usersDao;
	
	/*
	 * Performs save operation and returns user save Response
	 */

	@Override
	public ResponseEntity<ResponseStructure<Users>> saveUser(UsersDto usersDto) {
		Users user = new Users();
		user.setUserName(usersDto.getUserName());
		user.setUserEmail(usersDto.getUserEmail());
		user.setUserPassword(usersDto.getUserPassword());
		user.setUserPhone(usersDto.getUserPhone());
		user.setUserAddress(usersDto.getUserAddress());

		Users createdUser = usersDao.createUser(user);
		ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(" user saved succesfully");
		responseStructure.setData(createdUser);
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);

	}
	
	/*
	 * Performs get operation based on id and returns user fetched Response
	 */

	@Override
	public ResponseEntity<ResponseStructure<Users>> getUserById(int userId) {
		Users user = usersDao.getUserById(userId);
		if (user != null) {
			ResponseStructure<Users> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(" user found");
			responseStructure.setData(user);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UsersNotFoundException();
		}
	}

	
	/*
	 * Performs get operation based on name and returns user fetched Response
	 */
	@Override
	public ResponseEntity<ResponseStructure<Users>> getUserByName(String userName) {
		Users user = usersDao.getUserByName(userName);
		if (user != null) {
			ResponseStructure<Users> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(" user found");
			responseStructure.setData(user);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.OK);
		} else {
			throw new UsersNotFoundException();
		}
	}

	/*
	 * Performs delete operations on user based on id
	 */
	@Override
	public ResponseEntity<ResponseStructure<String>> deleteUser(int userId) {
		boolean user = usersDao.deleteUsers(userId);
		if (user) {
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("user deleted");
			responseStructure.setData("user removed successfully");
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		}
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("user not deleted");
		responseStructure.setData("user not removed");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Users>> updateUser(UsersDto usersDto, int UserId) {
		Users receivedUser = null;
		Users user = usersDao.getUserById(UserId);
		if (user != null) {
			if (usersDto.getUserName() != null) {
				user.setUserName(usersDto.getUserName());
			}
			if (usersDto.getUserEmail() != null) {
				user.setUserEmail(usersDto.getUserEmail());
			}
			if (usersDto.getUserPassword() != null) {
				user.setUserPassword(usersDto.getUserPassword());
			}
			if (usersDto.getUserPhone() != 0) {
				user.setUserPhone(usersDto.getUserPhone());
			}
			if (usersDto.getUserAddress() != null) {
				user.setUserAddress(usersDto.getUserAddress());
			}
			receivedUser = usersDao.createUser(user);
		} else {
			throw new UsersNotFoundException();
		}

		ResponseStructure<Users> responseStructure = new ResponseStructure<Users>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(" Admin updated succesfully");
		responseStructure.setData(receivedUser);
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);

	}

	
	/*
	 * Performs get operations on user and returns list of users
	 */
	@Override
	public ResponseEntity<ResponseStructure<List<Users>>> getAllUsers() {
		List<Users> userList = usersDao.getAllUsers();

		ResponseStructure<List<Users>> response = new ResponseStructure<List<Users>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Success");
		response.setData(userList);

		return new ResponseEntity<ResponseStructure<List<Users>>>(response, HttpStatus.OK);
	}

}
