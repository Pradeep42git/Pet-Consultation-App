package com.onlinepetconsultation.servicesimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.dao.AdminDao;
import com.onlinepetconsultation.dto.AdminDto;
import com.onlinepetconsultation.dto.JWTResponse;
import com.onlinepetconsultation.dto.ResponseStructure;
import com.onlinepetconsultation.dto.SignInRequest;
import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.exception.AdminNotExistException;
import com.onlinepetconsultation.services.AdminService;
import com.onlinepetconsultation.services.JWTService;

@Service
public class AdminServicesImp implements AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	@Qualifier("adminManager")
	private AuthenticationManager authenticationManager;
	@Autowired
	@Qualifier("admindetailservices")
	private UserDetailsService detailsService;
	@Autowired
	private JWTService jwtService;

	/*
	 * Performs save operation and returns Admin save Response
	 */
	@Override
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(AdminDto adminDto) {
		Admin admin = new Admin();
		admin.setName(adminDto.getName());
		admin.setEmail(adminDto.getEmail());
		admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		admin.setPhone(adminDto.getPhone());
		Admin createdAdmin = adminDao.createAdmin(admin);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(" Admin saved succesfully");
		responseStructure.setData(createdAdmin);
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.CREATED);

	}

	/*
	 * Performs get operation based on id and returns admin fetched Response
	 */

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
			throw new AdminNotExistException();
		}
	}

	/*
	 * Performs get operation based on name and returns admin fetched Response
	 */
	public ResponseEntity<ResponseStructure<Admin>> getByName(String adminName) {
		Admin admin = adminDao.getAdminByName(adminName);
		if (admin != null) {
			ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage(" admin found");
			responseStructure.setData(admin);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
		} else {
			throw new AdminNotExistException();
		}
	}
	/*
	 * Performs delete operations on admin based on id
	 */

	@Override
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

	/*
	 * Performs update operation and returns the updated admin based on
	 * name,email,password,phone
	 */

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(AdminDto adminDto, int adminId) {
		Admin receivedAdmin = null;
		Admin admin = adminDao.getAdminById(adminId);
		if (admin != null) {
			if (adminDto.getName() != null) {
				admin.setName(adminDto.getName());
			}
			if (adminDto.getEmail() != null) {
				admin.setEmail(adminDto.getEmail());
			}
			if (adminDto.getPassword() != null) {
				admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
			}
			if (adminDto.getPhone() != 0) {
				admin.setPhone(adminDto.getPhone());
			}
			receivedAdmin = adminDao.createAdmin(admin);
		} else {
			throw new AdminNotExistException();
		}

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(" Admin updated succesfully");
		responseStructure.setData(receivedAdmin);
		return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);

	}

	/*
	 * Performs Login operation for Admin
	 */
	@Override
	public ResponseEntity<ResponseStructure<JWTResponse>> adminLogin(SignInRequest request) {
		doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails = detailsService.loadUserByUsername(request.getEmail());
		String token = this.jwtService.generateToken(userDetails);
		JWTResponse jwtResponse = JWTResponse.builder().jwtToken(token).userName(userDetails.getUsername()).build();
		ResponseStructure<JWTResponse> responseStructure = new ResponseStructure<JWTResponse>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Login Successfully " + userDetails.getUsername());
		responseStructure.setData(jwtResponse);
		return new ResponseEntity<ResponseStructure<JWTResponse>>(responseStructure, HttpStatus.OK);
	}

	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);
		System.err.println(authenticationToken);
		
			try {
				Authentication authenticate = authenticationManager.authenticate(authenticationToken);
				System.err.println(authenticate);
			} catch (BadCredentialsException e) {
				throw new BadCredentialsException("Invalid Username or Password ..!!");
			}
	}

}