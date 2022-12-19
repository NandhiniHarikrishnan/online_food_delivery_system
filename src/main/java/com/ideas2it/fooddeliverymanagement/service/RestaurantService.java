package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDetailDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import java.util.List;

public interface RestaurantService {
    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO);

    List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException;

    RestaurantDetailDTO getRestaurantById(int id) throws FoodDeliveryManagementException;

    String deleteRestaurantById(int id) throws FoodDeliveryManagementException;

    String updateRestaurant(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException;

    List<RestaurantDetailDTO> searchRestaurant(String keyword) throws FoodDeliveryManagementException;
}
