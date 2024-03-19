package com.onlinepetconsultation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepetconsultation.entity.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {

}
