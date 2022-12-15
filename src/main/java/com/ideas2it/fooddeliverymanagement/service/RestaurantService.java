package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;

import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import java.util.List;


public interface RestaurantService {
     RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);
     List<RestaurantDTO> getRestaurants() throws FoodDeliveryManagementException;

     RestaurantDTO getRestaurantById(int id) throws FoodDeliveryManagementException;

     RestaurantDTO updateRestaurantById(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException;

     RestaurantDTO deleteRestaurantById(int id);
}
