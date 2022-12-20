package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.*;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantMapper;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.repository.RestaurantRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * {@inheritDoc}
     */
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException {
        RestaurantDTO createdRestaurantDTO = RestaurantMapper.convertRestaurantDTO(restaurantRepository
                .save(RestaurantMapper.convertRestaurant(restaurantDTO)));
        if(null == createdRestaurantDTO) {
            throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_ADDED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return createdRestaurantDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.findAll());
        List<RestaurantDetailDTO> restaurantDetailsDTO;
        if (restaurants.isEmpty()) {
            throw new FoodDeliveryManagementException(Constants.NO_RECORD_FOUND, HttpStatus.NOT_FOUND);
        }
        restaurantDetailsDTO = restaurants.stream()
                .map(r -> convertIntoRestaurantDetailDTO(r)).collect(Collectors.toList());
        return restaurantDetailsDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDetailDTO getRestaurantById(int id) throws FoodDeliveryManagementException {
        System.out.println(restaurantRepository.findById(id).orElseThrow(
                () -> new FoodDeliveryManagementException("REPOSITORY_NOT_FOUND", HttpStatus.NOT_FOUND)));
        return convertIntoRestaurantDetailDTO(RestaurantMapper.
                convertRestaurantDTO(restaurantRepository.findById(id).orElseThrow(
                () -> new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_FOUND, HttpStatus.NOT_FOUND))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteRestaurantById(int id) throws FoodDeliveryManagementException {
        if (!restaurantRepository.existsById(id)) {
            throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        restaurantRepository.deleteById(id);
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()) {
            throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_DELETED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "deleted successfully " + id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException {
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
                    throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_UPDATED, HttpStatus.NOT_FOUND);
                }
            }
        } else {
            throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return updatedRestaurantDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDetailDTO> searchRestaurant(String keyword) throws FoodDeliveryManagementException {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.searchRestaurant(keyword));
        System.out.println(restaurants);
        List<RestaurantDetailDTO> restaurantDetailsDTO;
        if (restaurants.isEmpty()) {
            throw new FoodDeliveryManagementException("NO_RECORD_FOUND", HttpStatus.NOT_FOUND);
        }
        restaurantDetailsDTO = restaurants.stream()
                .map(r -> convertIntoRestaurantDetailDTO(r)).collect(Collectors.toList());
        return restaurantDetailsDTO;
    }

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
