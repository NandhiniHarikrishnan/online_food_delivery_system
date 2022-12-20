package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantFoodRepository extends JpaRepository<RestaurantFood, Integer> {
    @Query(value = "select price from restaurant_food where food_id = :foodId and restaurant_id = :restaurantId", nativeQuery = true)
    float getPrice(@Param("foodId") int foodId, @Param("restaurantId") int restaurantId);
}
