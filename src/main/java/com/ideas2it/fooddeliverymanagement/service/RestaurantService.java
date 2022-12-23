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
     * To Create the new restaurant if the given values are valid that is
     * the restaurant name with same address is already exist or not.
     * </p>
     *
     * @param restaurantDTO - the restaurant values to be created
     * @return - the created restaurant
     * @throws FoodDeliveryManagementException - if the restaurant name with same address
     *                                           is already exist in the category table.
     */
    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To display all the existing restaurants stored in the restaurant table.
     * </p>
     *
     * @return - the list of existing restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants in the restaurant table
     */
    List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException;

    /**
     * <p>
     * checks the restaurant table whether it is soft deleted or not by using the restaurant id.
     * if it is present, return that restaurant of the given id.
     * </p>
     *
     * @param id - a restaurant id for which the restaurant to be returned
     * @return   - the restaurant if the restaurant id is found
     * @throws FoodDeliveryManagementException - if the restaurant is not found for the given id
     */
    RestaurantDetailDTO getRestaurantById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * checks the restaurant table whether it is soft deleted or not by using the restaurant id.
     * if it is present, set the isDeleted column as true to make soft delete.
     * </p>
     *
     * @param id - a restaurant id to be soft deleted
     * @return  - the success message if restaurant is deleted successfully
     * @throws FoodDeliveryManagementException - if the restaurant is not found for the given id
     */
    String deleteRestaurantById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * Get the existing restaurant object by using the restaurant id and set the values from the
     * restaurant object which is to be updated.
     * </p>
     *
     * @param  id - a restaurant id to be updated
     * @param restaurantDTO - the restaurant to be updated
     * @return - the updated restaurant
     * @throws FoodDeliveryManagementException - if the restaurant is not found for the given id
     *                                         - if the restaurant is not updated
     */
    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO, int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To fetch the list of restaurant based on it's name/keyword.
     * </p>
     *
     * @param keyword - an input for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given name/keyword
     */
    List<RestaurantDetailDTO> searchRestaurant(String keyword) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To fetch the list of restaurant based on it's location.
     * </p>
     *
     * @param location - the location for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given location
     */
    List<RestaurantDetailDTO> searchRestaurantByLocation(String location) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To fetch the list of restaurant based on it's cuisine name.
     * </p>
     *
     * @param cuisine - the cuisine for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given cuisine
     */
    List<RestaurantDetailDTO> searchRestaurantByCuisine(String cuisine) throws FoodDeliveryManagementException;
}
