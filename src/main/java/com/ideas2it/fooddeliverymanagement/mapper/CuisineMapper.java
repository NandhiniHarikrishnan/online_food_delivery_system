/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Class that used to convert the CuisineDto object to entity object
 * and vice versa
 * </p>
 *
 * @author Jeevanantham
 * @version 1.0
 * @since 16-DEC-2022
 */
public class CuisineMapper {

    /**
     * <p>
     * It converts a Cuisine object to a CuisineDTO object
     * <p>
     *
     * @param cuisine The cuisine object that needs to be converted to CuisineDTO.
     * @return A list of CuisineDTO objects
     */
    public static CuisineDTO convertCuisineDTO(Cuisine cuisine) {
        List<Restaurant> restaurants;
        CuisineDTO cuisineDTO = new CuisineDTO();
        if (null != cuisine) {
            cuisineDTO.setId(cuisine.getId());
            cuisineDTO.setCode(cuisine.getCode());
            cuisineDTO.setName(cuisine.getName());
           restaurants = cuisine.getRestaurants();
            if (null != restaurants) {
                cuisineDTO.setRestaurant(restaurants.stream()
                        .map(r -> r.getName()).collect(Collectors.joining(",")));
            }
        }
        return cuisineDTO;
    }

    /**
     * <p>
     * It converts a CuisineDTO object to a Cuisine object.
     * <p>
     *
     * @param cuisineDTO The DTO object that we want to convert to a Cuisine object.
     * @return A Cuisine object
     */
    public static Cuisine convertCuisine(CuisineDTO cuisineDTO) {
        List<RestaurantDTO> restaurantsDTO = null;
        List<Restaurant> restaurants ;
        Cuisine cuisine = new Cuisine();
        if (null != cuisineDTO) {
            cuisine.setId(cuisineDTO.getId());
            cuisine.setCode(cuisineDTO.getCode());
            cuisine.setName(cuisineDTO.getName());
            restaurantsDTO = cuisineDTO.getRestaurants();
            if (null != restaurantsDTO) {
                restaurants = restaurantsDTO.stream().map(r -> {
                    r.setCuisine(null);
                    return RestaurantMapper.convertRestaurant(r);
                }).collect(Collectors.toList());
                cuisine.setRestaurants(restaurants);
            }
        }
        return cuisine;
    }

    /**
     * <p>
     * It converts a list of Cuisine objects to a list of CuisineDTO objects.
     * <p>
     *
     * @param cuisines The list of cuisines to be converted.
     * @return A list of CuisineDTO objects.
     */
    public static List<CuisineDTO> convertCuisinesDTO(List<Cuisine> cuisines) {
        List<CuisineDTO> cuisinesDTO = new ArrayList<>();
        for (Cuisine cuisine  : cuisines) {
            cuisinesDTO.add(convertCuisineDTO(cuisine));
        }
        return cuisinesDTO;
    }
}





