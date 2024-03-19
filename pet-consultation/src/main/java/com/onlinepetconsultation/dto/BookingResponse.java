package com.onlinepetconsultation.dto;

import java.time.LocalDateTime;

import com.onlinepetconsultation.entity.Consultant;
import com.onlinepetconsultation.entity.Users;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookingResponse {
	
	private int Id;
	private LocalDateTime bookingDateTime;
	private Users user;
	private Consultant consultant;

}
