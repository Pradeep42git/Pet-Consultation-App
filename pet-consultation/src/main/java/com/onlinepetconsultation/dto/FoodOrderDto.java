package com.onlinepetconsultation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
 * this class is created to help save the food order by taking Integer List to select the 
 * product based on their ID.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrderDto {
	
	private boolean orderStatus;
	
	private List<Integer> products;

}
