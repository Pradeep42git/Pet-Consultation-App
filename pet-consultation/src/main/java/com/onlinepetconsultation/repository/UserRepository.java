package com.onlinepetconsultation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	public Users findByUserName(String name);

	Optional<Users> findByUserEmail(String email);
}
