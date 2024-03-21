package com.onlinepetconsultation.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onlinepetconsultation.util.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Admin implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "admin_id")
	@SequenceGenerator(name = "admin_id", initialValue = 1, allocationSize = 1, sequenceName = "admin_sequence")
	private int Id;
	
	@Size(min = 5, message = "Name should consist of at least 5 characters")
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid name format" )
	@NotNull
	private String name;
	
	@Email
	@Column(unique = true)
	@Pattern(regexp = "^[A-Za-z0-9@.]*$" ,message = "Invalid email format" )
	@NotNull
	private String email;
	
	@NotNull
	@Size(min = 3, message = "Password should consist of at least 3 characters")
//	@Pattern(regexp = "^[A-Za-z0-9@#$%&*]*$" ,message = "Invalid password format" )
	private String password;
	
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long phone;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(Roles.ADMIN.name()));
	}
	@Override
	public String getUsername() {
		
		return email;
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
