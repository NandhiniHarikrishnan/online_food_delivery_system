package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * Handles the converting process from entity to DTO for cuisine.
 * </p
 *
 * @author Jeevanantham
 * @version 1.0 14-DEC-2022
 */
@Component
public class CuisineMapper {

    private RestaurantMapper restaurantMapper;

    /**
     * <p>
     * To convert from entity to DTO for category
     * </p>
     *
     * @param cuisine - a category entity to be converted into DTO.
     * @return - a cuisine DTO.
     */
    public CuisineDTO convertCusineDTO(Cuisine cuisine) {
        List<Restaurant> restuarants = null;
        List<RestaurantDTO> restaurantsDTO = null;
        CuisineDTO cuisineDTO = new CuisineDTO();
        if (null != cuisine) {
            cuisineDTO.setId(cuisine.getId());
            cuisineDTO.setCode(cuisine.getCode());
            cuisineDTO.setName(cuisine.getName());

            if (null != restuarants) {
                restaurantsDTO = restuarants.stream().map((Function<Restaurant, RestaurantDTO>) r -> {
                    r.setCuisine(null);
                    return restaurantMapper.convertRestaurantDTO(r);
                }).collect(Collectors.toList());
            }
            cuisineDTO.setRestaurantDTOs(restaurantsDTO);
        }
        return cuisineDTO;
    }

    public Cuisine convertCuisine(CuisineDTO cuisineDTO) {
        List<RestaurantDTO> restaurantsDTO = null;
        List<Restaurant> restuarants = null;
        Cuisine cuisine = new Cuisine();
        if (null != cuisineDTO) {
            cuisine.setId(cuisineDTO.getId());
            cuisine.setCode(cuisineDTO.getCode());
            cuisine.setName(cuisineDTO.getName());
            if (null != restaurantsDTO) {
                restuarants = restaurantsDTO.stream().map(r -> {
                    r.setCuisineDTO(null);
                    return restaurantMapper.convertRestaurant(r);
                }).collect(Collectors.toList());
            }
            cuisine.setRestaurants(restuarants);
        }

        return cuisine;
    }

    public List<CuisineDTO> convertCuisinesDTO(List<Cuisine> cuisines) {
        List<CuisineDTO> cuisinesDTO = new ArrayList<>();
        for (Cuisine cuisine  : cuisines) {
            cuisinesDTO.add(convertCusineDTO(cuisine));
        }
        return cuisinesDTO;
    }
}























/*

 if (null != cuisine) {
         cuisineDTO.setId(cuisine.getId());
         cuisineDTO.setCode(cuisine.getCode());
         cuisineDTO.setName(cuisine.getName());
         if (null != restuarants) {
         for(Restaurant restaurant : restuarants) {
         restaurantDTOs.add(entityToDTO(restaurant));
         }
         }


         if (null != restuarantsDTOs) {
                for(RestaurantDTO restaurantDTO : restuarantDTOs) {
                    restaurants.add(entityToDTO(restaurantDTO));
                }
            }
        }
        return cuisine;*/
