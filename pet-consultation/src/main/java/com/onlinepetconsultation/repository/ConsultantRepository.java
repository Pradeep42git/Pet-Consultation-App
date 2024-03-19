package com.onlinepetconsultation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.util.ConsultantRoles;

public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {

	public List<Consultant> findByDesignation(ConsultantRoles designation);
}
