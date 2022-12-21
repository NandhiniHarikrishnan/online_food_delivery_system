package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDetailDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;

import java.util.List;

/**
 * Perform create, read, update, delete (CRUD),search operation for the restaurant.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-14
 */
public interface RestaurantService {

    /**
     * <p>
     * To add the new restaurant.
     * </p>
     *
     * @param restaurantDTO - the restaurant to be created
     * @return - the created restaurant.
     * @throws FoodDeliveryManagementException - if the restaurant is already exist
     */
    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To display all the restaurants.
     * </p>
     *
     * @return - the list of restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants in the table.
     */
    List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException;

    /**
     * <p>
     * To get the restaurant for the given restaurant id.
     * </p>
     *
     * @param id - a restaurant id for which the restaurant to be returned
     * @return   - the restaurant if the restaurant id is found
     * @throws FoodDeliveryManagementException - if the restaurant id is not found
     */
    RestaurantDetailDTO getRestaurantById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To remove the restaurant for the given restaurant id.
     * </p>
     *
     * @param id - a restaurant id to be removed
     * @return   - the success message if the restaurant is deleted
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    String deleteRestaurantById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To update the restaurant for the given restaurant id.
     * </p>
     *
     * @param  id - a restaurant id to be updated
     * @param restaurantDTO - the restaurant to be updated
     * @return    - the updated restaurant
     * @throws FoodDeliveryManagementException - if the restaurant is not found, if the restaurant is not updated
     */
    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To search the restaurant.
     * </p>
     *
     * @param keyword - an input for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given keyword
     */
    List<RestaurantDetailDTO> searchRestaurant(String keyword) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To search the restaurant based on it's location.
     * </p>
     *
     * @param location - the location for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given location
     */
    List<RestaurantDetailDTO> searchRestaurantByLocation(String location) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To search the restaurant based on it's cuisine.
     * </p>
     *
     * @param cuisine - the cuisine for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given cuisine
     */
    List<RestaurantDetailDTO> searchRestaurantByCuisine(String location) throws FoodDeliveryManagementException;
}
