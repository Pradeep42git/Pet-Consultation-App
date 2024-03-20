package com.onlinepetconsultation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.repository.ConsultantRepository;
import com.onlinepetconsultation.util.ConsultantRoles;

@Repository
public class ConsultantDao {

	@Autowired
	private ConsultantRepository consultantRepository;
	
	/*
	 * This method is used to create and return the consultant /
	 * and also update the details of the consultant
	 */
	public Consultant createConsultant(Consultant consultant) {
		if(consultant!=null) {
			return consultantRepository.save(consultant);
		}
		return null;
	}
	
	/*
	 * This method is used to get details of the Consultant by Consultant Id
	 */
	public Consultant getConsultantById(int consultantId) {
		Optional<Consultant> optional=consultantRepository.findById(consultantId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;			
	}
	
	/*
	 * This method is used to get All the Consultants list for Admin
	 */
	public List<Consultant> getAllConsultantForAdmin() {
		return consultantRepository.findAll();
	}
	
	/*
	 * This method is used to get All the Consultants list for User
	 */
	public List<Consultant> getAllConsultantForUsers() {
		return consultantRepository.findByIsAvailable(true);
	}
	
	/*
	 * This method is used to get All the Consultants list based on the designation
	 */
	public List<Consultant> getConsultantsByDesignation(ConsultantRoles designation){
		return consultantRepository.findByDesignationAndIsAvailable(designation,true);
	}
	

	/*
	 * This method is used to remove the Consultants list based on the Id
	 */
	public boolean removeConsultant(int consultantId) {
		Consultant consultant=this.getConsultantById(consultantId);
		if(consultant!=null) {
			consultantRepository.delete(consultant);
			return true;
		}
		return false;
	}
}
