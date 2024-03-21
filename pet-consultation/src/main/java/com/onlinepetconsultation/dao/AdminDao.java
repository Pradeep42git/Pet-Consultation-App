package com.onlinepetconsultation.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.repository.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;

	/*
	 * This method is used to create and return the admin / and also update the
	 * details of the admin
	 */
	public Admin createAdmin(Admin admin) {
		if (admin != null) {
			return adminRepository.save(admin);
		}
		return null;
	}

	/*
	 * This method is used to get details of the admin by admin Id
	 */
	public Admin getAdminById(int adminId) {
		Optional<Admin> admin = adminRepository.findById(adminId);
		if (admin.isPresent()) {
			return admin.get();
		}
		return null;
	}

	/*
	 * This method is used to get details of the admin by admin Name
	 */
	public Admin getAdminByName(String adminName) {
		Admin admin = adminRepository.findByName(adminName);
		if (admin != null) {
			return admin;
		}
		return null;
	}

	/*
	 * This method is used to delete the admin from the admin table
	 */
	public boolean deleteAdmin(int adminId) {
		Admin admin = this.getAdminById(adminId);
		if (admin != null) {
			adminRepository.delete(admin);
			return true;
		}
		return false;
	}

}
