package com.onlinepetconsultation.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.repository.ConsultantRepository;

@Repository
public class ConsultantDao {

	@Autowired
	private ConsultantRepository consultantRepository;
	
	
	public Consultant createConsultant(Consultant consultant) {
		if(consultant!=null) {
			return consultantRepository.save(consultant);
		}
		return null;
	}
	
	
	public Consultant getConsultant(int consultantId) {
		Optional<Consultant> optional=consultantRepository.findById(consultantId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;			
	}
	
	
	public boolean removeConsultant(int consultantId) {
		Consultant consultant=this.getConsultant(consultantId);
		if(consultant!=null) {
			consultantRepository.delete(consultant);
			return true;
		}
		return false;
	}
}
