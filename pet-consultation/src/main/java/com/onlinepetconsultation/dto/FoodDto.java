package com.onlinepetconsultation.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

	private boolean orderStatus;
	private double cost;
	private List<String> product_name;
}
