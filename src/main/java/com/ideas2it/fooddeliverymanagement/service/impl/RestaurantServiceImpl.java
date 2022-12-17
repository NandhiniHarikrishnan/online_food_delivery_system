package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.*;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantMapper;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.repository.RestaurantRepository;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * {@inheritDoc}
     */
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        return RestaurantMapper.convertRestaurantDTO(restaurantRepository.save(RestaurantMapper.convertRestaurant(restaurantDTO)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.findAll());
        List<RestaurantDetailDTO> restaurantDetailsDTO;
        if (restaurants.isEmpty()) {
            throw new FoodDeliveryManagementException("NO_RECORD_FOUND", HttpStatus.NOT_FOUND);
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
        return convertIntoRestaurantDetailDTO(RestaurantMapper.
                convertRestaurantDTO(restaurantRepository.findById(id).orElseThrow(
                () -> new FoodDeliveryManagementException("REPOSITORY_NOT_FOUND", HttpStatus.NOT_FOUND))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteRestaurantById(int id) throws FoodDeliveryManagementException {
        if (!restaurantRepository.existsById(id)) {
            throw new FoodDeliveryManagementException("REPOSITORY_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        restaurantRepository.deleteById(id);
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()) {
            throw new FoodDeliveryManagementException("DELETE_UNSUCCESSFUL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "deleted successfully " + id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateRestaurant(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException {
        String message = null;
        if (restaurantRepository.existsById(id)) {
            RestaurantDTO existingRestaurant = RestaurantMapper.convertRestaurantDTO(restaurantRepository.findById(id).get());
            if (null != existingRestaurant) {
                existingRestaurant.setName(restaurantDTO.getName());
                List<RestaurantFoodDTO> restaurantFoodsDTO = restaurantDTO.getRestaurantFoodDTOs();
                if (null != restaurantFoodsDTO) {
                    List<RestaurantFoodDTO> input = existingRestaurant.getRestaurantFoodDTOs();
                    System.out.println(input);
                    input.addAll(restaurantFoodsDTO);
                    System.out.println(input);
                    existingRestaurant.setRestaurantFoodDTOs(input);
                }
                List<AddressDTO> addressesDTO = restaurantDTO.getAddressesDTO();
                if (null != addressesDTO) {
                    List<AddressDTO> input = existingRestaurant.getAddressesDTO();
                    input.addAll(addressesDTO);
                    existingRestaurant.setAddressesDTO(input);
                }
                if(null != restaurantDTO.getCuisineDTO()) {
                    existingRestaurant.setCuisineDTO(restaurantDTO.getCuisineDTO());
                }
                System.out.println("existing dto" + existingRestaurant);
                System.out.println("converted dto" + RestaurantMapper.convertRestaurant(existingRestaurant));
                restaurantRepository.save(RestaurantMapper.convertRestaurant(existingRestaurant));
                message = restaurantDTO.getName() + " Update Successfully";
            }
        } else {
            throw new FoodDeliveryManagementException("REPOSITORY_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return message;
    }

    private RestaurantDetailDTO convertIntoRestaurantDetailDTO(RestaurantDTO restaurantDTO) {
        RestaurantDetailDTO restaurantDetailDTO = new RestaurantDetailDTO();
        restaurantDetailDTO.setId(restaurantDTO.getId());
        restaurantDetailDTO.setName(restaurantDTO.getName());
        List<RestaurantFoodDTO> restaurantFoodsDTO = restaurantDTO.getRestaurantFoodDTOs();
        if (null != restaurantFoodsDTO) {
            List<FoodDTO> foodsDTO = restaurantFoodsDTO.stream().map(restaurantFoodDTO -> {
                FoodDTO foodDTO = restaurantFoodDTO.getFoodDTO();
                if(null != foodDTO) {
                    foodDTO.setPrice(restaurantFoodDTO.getPrice());
                    foodDTO.setRestaurantFoods(null);
                }
                return foodDTO;
            }).collect(Collectors.toList());
            restaurantDetailDTO.setFoodsDTO(foodsDTO);
        }
        restaurantDetailDTO.setCuisineDTO(restaurantDTO.getCuisineDTO());
        restaurantDetailDTO.setAddressesDTO(restaurantDTO.getAddressesDTO());
        return restaurantDetailDTO;
    }
}
