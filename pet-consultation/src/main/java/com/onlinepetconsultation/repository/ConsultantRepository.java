package com.onlinepetconsultation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.util.ConsultantRoles;

public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {

	public List<Consultant> findByDesignationAndIsAvailable(ConsultantRoles designation,boolean status);
	
	public List<Consultant> findByIsAvailable(boolean status);
}
