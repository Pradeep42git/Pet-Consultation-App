package com.onlinepetconsultation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

public Users findByName(String name);
	
	Optional<Users> findByEmailAndPassword(String email,String password);
}
