package com.onlinepetconsultation.entity;

import java.util.List;

import com.onlinepetconsultation.util.ConsultantRoles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Consultant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "consultant_id")
	@SequenceGenerator(name = "consultant_id", initialValue = 1, allocationSize = 1, sequenceName = "consultant_sequence")
	private int Id;
	@Size(min = 5, message = "Name should consist of at least 5 characters")
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$", message = "Invalid name format")
	@NotNull
	private String name;
	@Email
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@.]*$", message = "Invalid email format")
	@NotNull
	private String email;
	@NotNull
	private String address;
	@NotNull
	@Enumerated(EnumType.STRING)
	private ConsultantRoles designation;
	@OneToMany(mappedBy = "consultant", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Booking> booking;
}
