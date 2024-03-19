package com.onlinepetconsultation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByName(String name);
}
