package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;

import java.util.List;

/**
 * It performs create, read, update, delete (CRUD) for the address
 *
 * @author - jeevanantham
 * @version - 1.0
 * @since - 2022-12-10
 */
public interface FoodService {

    /**
     * It searches for food items in the database based on the value passed as a parameter
     *
     * @param value The value to be searched.
     * @return List of FoodDTO objects
     * @throws FoodDeliveryManagementException - if the food is not found
     */
    List<FoodDTO> searchFood(String value) throws FoodDeliveryManagementException;

    /**
     * This function deletes a food item from the database based on the id of the food item
     *
     * @param id The id of the food item to be deleted.
     * @return String
     * @throws FoodDeliveryManagementException - when the Id is null, throw custom
     *                               exception to controller
     */
    String deleteFoodById(int id) throws FoodDeliveryManagementException;

    /**
     * It updates the food item with the given id.
     *
     * @param foodDTO This is the food object that you want to update.
     * @param id The id of the food item to be updated.
     * @return FoodDTO
     * @throws FoodDeliveryManagementException- when the Id is null, throw custom
     *                            exception to controller
     */
    FoodDTO updateFoodById(FoodDTO foodDTO, int id) throws FoodDeliveryManagementException;

    /**
     * This function returns a foodDTO object with the given id
     *
     * @param id The id of the food to be fetched.
     * @return CuisineDTO
     * @throws FoodDeliveryManagementException- when the Id is null, throw custom
     *                            exception to controller
     */
    FoodDTO getFoodById(int id) throws FoodDeliveryManagementException;

    /**
     * It searches for food items in the database based on the value passed as a parameter
     *
     * @param name The value to be searched.
     * @return List of FoodDTO based on category objects
     * @throws FoodDeliveryManagementException - if the food is not found
     */
    List<FoodDTO> searchFoodByCategory(String name) throws FoodDeliveryManagementException;
}
