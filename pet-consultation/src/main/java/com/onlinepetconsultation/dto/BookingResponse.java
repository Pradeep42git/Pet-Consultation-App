package com.onlinepetconsultation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.entity.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BookingResponse {
	
	private int Id;
	private LocalDateTime bookingDateTime;
	@JsonIgnore
	private Users user;
	@JsonIgnore
	private Consultant consultant;

}
