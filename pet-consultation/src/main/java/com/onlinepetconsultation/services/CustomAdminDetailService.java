package com.onlinepetconsultation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.repository.AdminRepository;
import com.onlinepetconsultation.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service(value = "admindetailservices")
@RequiredArgsConstructor
@Slf4j
public class CustomAdminDetailService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserDetails details=adminRepository.findByEmail(username).orElse(null);
		log.info(""+details);
		if(details!=null) {
		return details;
		}
		return this.loadUserByUsernames(username);
	}

	public UserDetails loadUserByUsernames(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		log.info("Username : {}", username);
		return userRepository.findByUserEmail(username).orElse(null);
	}
}
