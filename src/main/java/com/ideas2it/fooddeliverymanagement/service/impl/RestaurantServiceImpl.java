package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantMapper;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.repository.RestaurantRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantMapper restaurantMapper;

    /**
     * {@inheritDoc}
     */
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantMapper.convertRestaurantDTO(restaurantRepository
                .save(restaurantMapper.convertRestaurant((restaurantDTO))));
    }

    public List<RestaurantDTO> getRestaurants() throws FoodDeliveryManagementException{
        List<Restaurant> restaurants =restaurantRepository.findAll();
        if(restaurants.isEmpty()) {
            throw new FoodDeliveryManagementException("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return restaurantMapper.convertIntoRestaurantsDTO(restaurants);
    }

    public RestaurantDTO getRestaurantById(int id) throws FoodDeliveryManagementException{
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(!restaurant.isPresent()) {
            throw new FoodDeliveryManagementException("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return restaurantMapper.convertRestaurantDTO(restaurant.get());
    }

    @Override
    public RestaurantDTO updateRestaurantById(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException {
        return null;
    }

    @Override
    public RestaurantDTO deleteRestaurantById(int id) {
        return null;
    }

}
