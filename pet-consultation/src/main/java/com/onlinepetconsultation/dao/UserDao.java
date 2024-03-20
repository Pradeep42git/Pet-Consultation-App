package com.onlinepetconsultation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.entity.Product;
import com.onlinepetconsultation.entity.Users;
import com.onlinepetconsultation.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;

	public Users createUser(Users user) {
		if (user != null) {
			return userRepository.save(user);
		}
		return null;
	}

	public Users getUserById(int userId) {
		Optional<Users> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	public Users getUserByName(String userName) {
		Users user = userRepository.findByName(userName);
		if (user != null) {
			return user;
		}
		return null;
	}

	public boolean deleteUsers(int userId) {
		Users user = this.getUserById(userId);
		if (user != null) {
			userRepository.delete(user);
			return true;
		}
		return false;
	}

	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}

}
