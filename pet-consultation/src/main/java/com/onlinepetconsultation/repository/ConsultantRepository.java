package com.onlinepetconsultation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.Consultant;

public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {

}
