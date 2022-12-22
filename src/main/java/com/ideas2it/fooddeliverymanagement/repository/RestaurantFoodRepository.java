/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * RestaurantFoodRepository used to get the price of food
 * each restaurant has different price so based on the restaurant id
 * get the food price and total price of order.
 */
public interface RestaurantFoodRepository extends JpaRepository<RestaurantFood, Integer> {
    @Query(value = "select price from restaurant_food where food_id = :foodId and restaurant_id = :restaurantId", nativeQuery = true)
    float getPrice(@Param("foodId") int foodId, @Param("restaurantId") int restaurantId);
}
