package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantFoodRepository extends JpaRepository<RestaurantFood, Integer> {
}
