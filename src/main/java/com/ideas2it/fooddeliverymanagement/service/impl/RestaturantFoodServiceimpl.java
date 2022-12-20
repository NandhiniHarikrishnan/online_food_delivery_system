package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import com.ideas2it.fooddeliverymanagement.repository.RestaurantFoodRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaturantFoodServiceimpl implements RestaurantFoodService {

    @Autowired
    private RestaurantFoodRepository restaurantFoodRepository;

    @Override
    public float getPrice(int foodId, int restaurantId) {
        return restaurantFoodRepository.getPrice(foodId, restaurantId);
    }
}
