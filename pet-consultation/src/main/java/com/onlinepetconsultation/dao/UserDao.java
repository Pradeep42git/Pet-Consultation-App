package com.onlinepetconsultation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;
}
