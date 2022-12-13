package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {

    public RestaurantDTO entityToDTO(Restaurant restaurant) {

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setAddresses(restaurant.getAddresses());
        restaurantDTO.setRestaurantFoods(restaurant.getRestaurantFoods());

        return restaurantDTO;
    }

    public Restaurant DTOToEntity(RestaurantDTO restaurantDTO) {

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddresses(restaurantDTO.getAddresses());
        restaurant.setRestaurantFoods(restaurantDTO.getRestaurantFoods());

        return restaurant;
    }

    private List<Restaurant> convertDTOToEntity(List<RestaurantDTO> restaurantDTOList) {
        List<Restaurant> restaurants = new ArrayList<>();

        for (RestaurantDTO restaurantDTO : restaurantDTOList) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(restaurantDTO.getId());
            restaurant.setName(restaurantDTO.getName());
            restaurant.setAddresses(restaurantDTO.getAddresses());
            restaurant.setRestaurantFoods(restaurantDTO.getRestaurantFoods());

        }
        return restaurants;
    }


}
