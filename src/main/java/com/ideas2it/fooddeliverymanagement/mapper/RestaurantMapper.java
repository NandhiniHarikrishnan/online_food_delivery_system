package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Handles the converting process from entity to DTO for Restaurant.
 * </p
 *
 * @author Jeevanantham
 * @version 1.0 13-DEC-2022
 */

public class RestaurantMapper {

    /**
     * It converts a Restaurant object into a RestaurantDTO object.
     *
     * @param restaurant The restaurant object that needs to be converted into a restaurantDTO object.
     * @return A RestaurantDTO object
     */
    public static RestaurantDTO convertRestaurantDTO(Restaurant restaurant) {
        RestaurantFoodMapper restaurantFoodMapper = new RestaurantFoodMapper();

        RestaurantDTO restaurantDTO = null;
        List<Address> addresses;
        List<AddressDTO> addressDTOs = new ArrayList<>();
        List<RestaurantFood> restaurantFoods;
        List<RestaurantFoodDTO> restaurantFoodDTOs = new ArrayList<>();
        Cuisine cuisine;
        if (null != restaurant) {
            restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(restaurant.getId());
            restaurantDTO.setName(restaurant.getName());
            cuisine = restaurant.getCuisine();
            if (null != cuisine) {
                cuisine.setRestaurants(null);
                restaurantDTO.setCuisine(CuisineMapper.convertCuisineDTO(cuisine));
            }
            addresses = restaurant.getAddresses();
            if (null != addresses) {
                for (Address address : addresses) {
                    address.setRestaurant(null);
                    addressDTOs.add(UserMapper.convertAddressDTO(address));
                }
                restaurantDTO.setAddresses(addressDTOs);
            }
            restaurantFoods = restaurant.getRestaurantFoods();
            if (null != restaurantFoods) {
                for (RestaurantFood restaurantFood : restaurantFoods) {
                    restaurantFood.setRestaurant(null);
                    restaurantFoodDTOs.add(restaurantFoodMapper.convertIntoDTO(restaurantFood));
                }
                restaurantDTO.setRestaurantFoods(restaurantFoodDTOs);
            }
        }
        return restaurantDTO;
    }

    /**
     * It converts a RestaurantDTO object into a Restaurant object
     *
     * @param restaurantDTO The DTO object that we want to convert into an entity object.
     * @return A Restaurant object
     */
    public static Restaurant convertRestaurant(RestaurantDTO restaurantDTO) {
        RestaurantFoodMapper restaurantFoodMapper = new RestaurantFoodMapper();
        Restaurant restaurant = null;
        List<AddressDTO> addressDTOs;
        List<RestaurantFoodDTO> restaurantFoodDTOs;
        CuisineDTO cuisineDTO;
        List<Address> addresses = new ArrayList<>();
        List<RestaurantFood> restaurantFoods = new ArrayList<>();
        if (null != restaurantDTO) {
            restaurant = new Restaurant();
            restaurant.setId(restaurantDTO.getId());
            restaurant.setName(restaurantDTO.getName());

            cuisineDTO = restaurantDTO.getCuisine();
            if (null != cuisineDTO) {
                cuisineDTO.setRestaurants(null);
                restaurant.setCuisine(CuisineMapper.convertCuisine(cuisineDTO));
            }
            addressDTOs = restaurantDTO.getAddresses();
            if (null != addressDTOs) {
                for (AddressDTO addressDTO : addressDTOs) {
                    addresses.add(UserMapper.convertAddress(addressDTO));
                }
                restaurant.setAddresses(addresses);
            }
            restaurantFoodDTOs = restaurantDTO.getRestaurantFoods();
            if (null != restaurantFoodDTOs) {
                for (RestaurantFoodDTO restaurantFoodDTO : restaurantFoodDTOs) {
                    //restaurantFoodDTO.setRestaurant(null);
                    restaurantFoods.add(restaurantFoodMapper.convertIntoEntity(restaurantFoodDTO));
                }
                restaurant.setRestaurantFoods(restaurantFoods);
            }
        }
        return restaurant;
    }

    /**
     * > This function converts a list of restaurants into a list of restaurant DTOs
     *
     * @param restaurants The list of restaurants that we want to convert into a list of RestaurantDTOs.
     * @return A list of RestaurantDTO objects.
     */
    public static List<RestaurantDTO> convertIntoRestaurantsDTO(List<Restaurant> restaurants) {
        List<RestaurantDTO> restaurantDto = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDto.add(convertRestaurantDTO(restaurant));
        }
        return restaurantDto;
    }
}












