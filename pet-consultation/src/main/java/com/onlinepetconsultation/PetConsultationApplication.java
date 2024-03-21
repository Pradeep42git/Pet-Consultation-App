package com.onlinepetconsultation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.onlinepetconsultation.entity.Admin;
import com.onlinepetconsultation.repository.AdminRepository;

@SpringBootApplication
public class PetConsultationApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(PetConsultationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (adminRepository.count() == 0) {
			Admin admin = new Admin();
			admin.setName("admin");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("Admin@123"));
			admin.setPhone(9999999999l);

			adminRepository.save(admin);
		}
	}

}
