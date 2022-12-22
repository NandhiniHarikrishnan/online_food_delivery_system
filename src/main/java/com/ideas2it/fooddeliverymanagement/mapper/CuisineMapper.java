/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Handles the converting process from entity to DTO for cuisine.
 * </p
 *
 * @author Jeevanantham
 * @version 1.0
 * @Since 16-DEC-2022
 */
public class CuisineMapper {

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
     * It converts a CuisineDTO object to a Cuisine object.
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
     * It converts a list of Cuisine objects to a list of CuisineDTO objects.
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





