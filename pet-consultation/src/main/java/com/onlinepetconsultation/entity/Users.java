package com.onlinepetconsultation.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinepetconsultation.util.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Component
public class Users implements UserDetails {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
	@SequenceGenerator(name = "user_id", initialValue = 1, allocationSize = 1, sequenceName = "user_sequence")
	private int userId;
	
	@Size(min = 5, message = "Name should consist of at least 5 characters")
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid name format" )
	@NotNull
	private String userName;
	
	@Email
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@.]*$" ,message = "Invalid email format" )
	@NotNull
	private String userEmail;
	
	@NotNull
	@Size(min = 3, message = "Password should consist of at least 3 characters")
//	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid password format" )
	private String password;
	
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long userPhone;
	
	private String userAddress;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	List<Booking> booking;
	

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore/*
	 * Performs delete operations on admin based on id
	 */
	List<FoodOrder> foodOrders;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(Roles.USER.name()));
	}
	@Override
	public String getUsername() {
		
		return userEmail;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}



	
	
	
}
