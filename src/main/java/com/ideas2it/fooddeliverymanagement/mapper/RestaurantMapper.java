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
    private UserMapper userMapper = new UserMapper();
    private CuisineMapper cuisineMapper = new CuisineMapper();

    public RestaurantDTO entityToDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = null;
        List<Address> addresses = null;
        List<AddressDTO> addressDTOs = null;
        List<RestaurantFood> restaurantFoods = null;
        List<RestaurantFoodDTO> restaurantFoodDTOs = null;
        Cuisine cuisine = null;
        if (null != restaurant) {
            restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(restaurant.getId());
            restaurantDTO.setName(restaurant.getName());
            cuisine = restaurant.getCuisine();
            if (null != cuisine) {
                cuisine.setRestaurants(null);
                restaurantDTO.setCuisineDTO(cuisineMapper.entityToDTO(cuisine));

            }
            addresses = restaurant.getAddresses();
            if (null != addresses) {
                for (Address address : addresses) {
                    addressDTOs.add(userMapper.convertAddressDTO(address));
                }
            }
            restaurantFoods = restaurant.getRestaurantFoods();
            if (null != restaurantFoods) {
                for (RestaurantFood restaurantFood : restaurantFoods) {
                    restaurantFoodDTOs.add(convertEntityDTO(restaurantFood));
                }
            }

        }
        return restaurantDTO;
    }

    public Restaurant dTOToEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = null;
        List<Address> addresses = null;
        List<AddressDTO> addressDTOs = null;
        List<RestaurantFood> restaurantFoods = null;
        List<RestaurantFoodDTO> restaurantFoodDTOs = null;
        CuisineDTO cuisineDTO = null;
        if (null != restaurantDTO) {
            restaurant = new Restaurant();
            restaurant.setId(restaurantDTO.getId());
            restaurant.setName(restaurantDTO.getName());

            cuisineDTO = restaurantDTO.getCuisineDTO();
            if (null != cuisineDTO) {
                cuisineDTO.setRestaurantDTOs(null);
                restaurant.setCuisine(cuisineMapper.dtoToEntity(cuisineDTO));

            }

            addressDTOs = restaurantDTO.getAddressesDTO();
            if (null != addressDTOs) {
                for (AddressDTO addressDTO : addressDTOs) {
                    addresses.add(userMapper.convertAddress(addressDTO));
                }
            }
            restaurantFoodDTOs = restaurantDTO.getRestaurantFoodDTOs();
            if (null != restaurantFoodDTOs) {
                for (RestaurantFoodDTO restaurantFoodDTO : restaurantFoodDTOs) {
                    restaurantFoods.add(convertEntity(restaurantFoodDTO));
                }
            }
        }
        return restaurant;
    }

    public List<RestaurantDTO> convertIntoRestaurantsDTO(List<Restaurant> restaurants) {
        List<RestaurantDTO> restaurantDto = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDto.add(entityToDTO(restaurant));
        }
        return restaurantDto;
    }

    public RestaurantFoodDTO convertEntityDTO(RestaurantFood restaurantFood) {
        RestaurantFoodDTO restaurantFoodDTO = new RestaurantFoodDTO();
        restaurantFoodDTO.setPrice(restaurantFood.getPrice());
        return restaurantFoodDTO;
    }

    public RestaurantFood convertEntity(RestaurantFoodDTO restaurantFoodDTO) {
        RestaurantFood restaurantFood = new RestaurantFood();
        restaurantFood.setPrice(restaurantFoodDTO.getPrice());
        return restaurantFood;
    }
}













    /*private List<Restaurant> convertDTOToEntity(List<RestaurantDTO> restaurantDTOList) {
        List<Restaurant> restaurants = new ArrayList<>();

        for (RestaurantDTO restaurantDTO : restaurantDTOList) {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(restaurantDTO.getId());
            restaurant.setName(restaurantDTO.getName());
            restaurant.setAddresses(restaurantDTO.getAddresses());
            restaurant.setRestaurantFoods(restaurantDTO.getRestaurantFoods());
        }
        return restaurants;
    }*/