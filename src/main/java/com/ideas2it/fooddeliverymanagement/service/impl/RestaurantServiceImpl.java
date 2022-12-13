package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantMapper;
import com.ideas2it.fooddeliverymanagement.repository.RestaurantRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    RestaurantMapper restaurantMapper;
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantMapper.entityToDTO(restaurantRepository.save(restaurantMapper.DTOToEntity(restaurantDTO)));
    }
}
