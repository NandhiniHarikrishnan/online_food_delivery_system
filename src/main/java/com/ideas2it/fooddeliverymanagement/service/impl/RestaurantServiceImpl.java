/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.*;
import com.ideas2it.fooddeliverymanagement.exception.ResourceExistException;
import com.ideas2it.fooddeliverymanagement.exception.ResourceNotFoundException;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantMapper;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.repository.RestaurantRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * It performs create, read, update, delete (CRUD) , search operation for the restaurant and
 * throws custom exception if the restaurant is not present in the database
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-15
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * {@inheritDoc}
     */
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException,ResourceExistException {
        if(isRestaurantExist(restaurantDTO)) {
            throw new ResourceExistException(Constants.RESTAURANT_EXIST);
        }
        RestaurantDTO createdRestaurantDTO = RestaurantMapper.convertRestaurantDTO(restaurantRepository
                .save(RestaurantMapper.convertRestaurant(restaurantDTO)));
        if(null == createdRestaurantDTO) {
            throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_ADDED);
        }
        return createdRestaurantDTO;
    }

    /**
     * <p>
     * To check the restaurant is already present or not.
     * </p>
     *
     * @param  restaurantDTO - the restaurant to be checked the presence
     * @return    - true if the restaurant is present
     *            - false otherwise
     * @throws FoodDeliveryManagementException - if the restaurant is not found, if the restaurant is not updated
     */
    private boolean isRestaurantExist(RestaurantDTO restaurantDTO) {
        boolean isPresent = false;
        List<Restaurant> existingRestaurants = restaurantRepository.findByName(restaurantDTO.getName());
        if (null != existingRestaurants) {
            for (Restaurant restaurant : existingRestaurants) {
                isPresent = restaurant.getAddresses().stream().anyMatch(address ->
                        address.getStreet().equals(restaurantDTO.getAddresses().get(0).getStreet())
                                && address.getPinCode() == restaurantDTO.getAddresses().get(0).getPinCode()
                );
                if(isPresent) {
                    break;
                }
            }
        }
        return isPresent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDetailDTO> getRestaurants() {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.findAll());
        return restaurants.stream()
                .map(r -> convertIntoRestaurantDetailDTO(r)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDetailDTO getRestaurantById(int id) throws ResourceNotFoundException {
        return convertIntoRestaurantDetailDTO(RestaurantMapper.
                convertRestaurantDTO(restaurantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.RESTAURANT_NOT_FOUND))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Restaurant> deleteRestaurantById(int id) throws ResourceNotFoundException {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException(Constants.RESTAURANT_NOT_FOUND);
        }
        restaurantRepository.deleteById(id);
        return restaurantRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException,ResourceNotFoundException{
        RestaurantDTO updatedRestaurantDTO = null;
        if (restaurantRepository.existsById(id)) {
            RestaurantDTO existingRestaurant = RestaurantMapper.convertRestaurantDTO(restaurantRepository.findById(id).get());
            if (null != existingRestaurant) {
                existingRestaurant.setName(restaurantDTO.getName());
                List<RestaurantFoodDTO> restaurantFoodsDTO = restaurantDTO.getRestaurantFoods();
                if (null != restaurantFoodsDTO) {
                    List<RestaurantFoodDTO> input = existingRestaurant.getRestaurantFoods();
                    input.addAll(restaurantFoodsDTO);
                    existingRestaurant.setRestaurantFoods(input);
                }
                List<AddressDTO> addressesDTO = restaurantDTO.getAddresses();
                if (null != addressesDTO) {
                    List<AddressDTO> input = existingRestaurant.getAddresses();
                    input.addAll(addressesDTO);
                    existingRestaurant.setAddresses(input);
                }
                if(null != restaurantDTO.getCuisine()) {
                    existingRestaurant.setCuisine(restaurantDTO.getCuisine());
                }
                updatedRestaurantDTO =  RestaurantMapper.convertRestaurantDTO(restaurantRepository
                        .save(RestaurantMapper.convertRestaurant(existingRestaurant)));
                if(null == updatedRestaurantDTO) {
                    throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_UPDATED);
                }
            }
        } else {
            throw new ResourceNotFoundException(Constants.RESTAURANT_NOT_FOUND);
        }
        return updatedRestaurantDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDetailDTO> searchRestaurant(String keyword) {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.searchRestaurant(keyword));
        return restaurants.stream()
                .map(r -> convertIntoRestaurantDetailDTO(r)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDetailDTO> searchRestaurantByLocation(String location) {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.searchRestaurantByLocation(location));
        return restaurants.stream()
                .map(r -> convertIntoRestaurantDetailDTO(r)).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDetailDTO> searchRestaurantByCuisine(String cuisine) {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.searchRestaurantByCuisine(cuisine));
        return restaurants.stream().map(r ->
                convertIntoRestaurantDetailDTO(r)).collect(Collectors.toList());
    }

    /**
     * <p>
     * To convert the restaurant DTO into user defined restaurant DTO
     * </p>
     *
     * @param  restaurantDTO - the restaurant to be converted into user defined restaurant DTO
     * @return    - the converted user defined restaurant
     */
    private RestaurantDetailDTO convertIntoRestaurantDetailDTO(RestaurantDTO restaurantDTO) {
        RestaurantDetailDTO restaurantDetailDTO = new RestaurantDetailDTO();
        restaurantDetailDTO.setId(restaurantDTO.getId());
        restaurantDetailDTO.setName(restaurantDTO.getName());
        List<RestaurantFoodDTO> restaurantFoodsDTO = restaurantDTO.getRestaurantFoods();
        if (null != restaurantFoodsDTO) {
            List<FoodDTO> foodsDTO = restaurantFoodsDTO.stream().map(restaurantFoodDTO -> {
                FoodDTO foodDTO = restaurantFoodDTO.getFood();
                if(null != foodDTO) {
                    foodDTO.setPrice(restaurantFoodDTO.getPrice());
                    foodDTO.setRestaurantFoods(null);
                }
                return foodDTO;
            }).collect(Collectors.toList());
            restaurantDetailDTO.setFoods(foodsDTO);
            System.out.println(foodsDTO);
        }
        restaurantDetailDTO.setCuisine(restaurantDTO.getCuisine());
        restaurantDetailDTO.setAddresses(restaurantDTO.getAddresses());
        return restaurantDetailDTO;
    }
}
