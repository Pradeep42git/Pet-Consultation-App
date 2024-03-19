package com.onlinepetconsultation.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "foodorder_id")
	@SequenceGenerator(name = "foodorder_id", initialValue = 100, allocationSize = 1, sequenceName = "foodorder_sequence")
	private int Id;
	
	
	private LocalDateTime foodorderDateTime;
	
	private boolean orderStatus;
	
	
	@NotNull
	@Positive
	private double cost;

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonIgnore
	private List<Product> products;

}
