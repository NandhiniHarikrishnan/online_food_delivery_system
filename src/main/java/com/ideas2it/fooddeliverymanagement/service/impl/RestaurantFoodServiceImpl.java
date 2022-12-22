/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.repository.RestaurantFoodRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RestaurantFoodServiceImpl method is used to get extract price from restaurant
 * by passing restaurant id and food id to get extract price from restaurant.
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
@Service
public class RestaurantFoodServiceImpl implements RestaurantFoodService {
    private RestaurantFoodRepository restaurantFoodRepository;
    @Autowired
    public RestaurantFoodServiceImpl(RestaurantFoodRepository restaurantFoodRepository) {
        this.restaurantFoodRepository = restaurantFoodRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPrice(int foodId, int restaurantId) {
        return restaurantFoodRepository.getPrice(foodId, restaurantId);
    }
}
