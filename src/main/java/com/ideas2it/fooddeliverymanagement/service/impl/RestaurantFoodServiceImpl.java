package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantFoodMapper;
import com.ideas2it.fooddeliverymanagement.repository.RestaurantFoodRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantFoodServiceImpl implements RestaurantFoodService {

    @Autowired
    private RestaurantFoodRepository restaurantFoodRepository;

    @Autowired
    private RestaurantFoodMapper restaurantFoodMapper;

    public RestaurantFoodDTO addFood(RestaurantFoodDTO restaurantFoodDTO) {
        return restaurantFoodMapper.convertIntoDTO(restaurantFoodRepository.save(restaurantFoodMapper.convertIntoEntity(restaurantFoodDTO)));
    }
}
