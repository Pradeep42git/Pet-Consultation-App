package com.onlinepetconsultation.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "booking_id")
	@SequenceGenerator(name = "booking_id", initialValue = 100, allocationSize = 1, sequenceName = "booking_sequence")
	private int Id;
	
	@CreationTimestamp
	private LocalDateTime bookingDateTime;
	
	@NotNull
	@ManyToOne
	@JoinColumn
	private Users user;
	
	@NotNull
	@ManyToOne
	@JoinColumn
	private Consultant consultant;

	@Override
	public String toString() {
		return "Booking [Id=" + Id + ", bookingDateTime=" + bookingDateTime + " ]";
	}
}
