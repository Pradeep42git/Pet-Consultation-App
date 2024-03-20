package com.onlinepetconsultation.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrderDto {

	
	
	private LocalDateTime foodorderDateTime;
	private boolean orderStatus;
	
	@NotNull
	@Positive
	private double cost;

	
	private List<Integer> products;

}
