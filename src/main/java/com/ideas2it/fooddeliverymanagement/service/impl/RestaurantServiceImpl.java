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

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * {@inheritDoc}
     */
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException {
        if(isRestaurantExist(restaurantDTO)) {
            throw new FoodDeliveryManagementException(Constants.RESTAURANT_EXIST, HttpStatus.FOUND);
        }
        RestaurantDTO createdRestaurantDTO = RestaurantMapper.convertRestaurantDTO(restaurantRepository
                .save(RestaurantMapper.convertRestaurant(restaurantDTO)));
        if(null == createdRestaurantDTO) {
            throw new FoodDeliveryManagementException(Constants.RESTAURANT_NOT_ADDED, HttpStatus.INTERNAL_SERVER_ERROR);
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
        return Constants.DELETED_SUCCESSFULLY + id;
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
    public List<RestaurantDetailDTO> searchRestaurantByLocation(String location) throws FoodDeliveryManagementException {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.searchRestaurantByLocation(location));
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
    public List<RestaurantDetailDTO> searchRestaurantByCuisine(String cuisine) throws FoodDeliveryManagementException {
        List<RestaurantDTO> restaurants = RestaurantMapper.convertIntoRestaurantsDTO(restaurantRepository.searchRestaurantByCuisine(cuisine));
        List<RestaurantDetailDTO> restaurantDetailsDTO;
        if (restaurants.isEmpty()) {
            throw new FoodDeliveryManagementException("NO_RECORD_FOUND", HttpStatus.NOT_FOUND);
        }
        restaurantDetailsDTO = restaurants.stream()
                .map(r -> convertIntoRestaurantDetailDTO(r)).collect(Collectors.toList());
        return restaurantDetailsDTO;
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
