package com.onlinepetconsultation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
