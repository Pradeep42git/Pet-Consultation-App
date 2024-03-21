package com.onlinepetconsultation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinepetconsultation.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service(value = "admindetailservices")
@RequiredArgsConstructor
public class CustomAdminDetailService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return adminRepository.findByEmail(username).orElse(null);
	}

}
