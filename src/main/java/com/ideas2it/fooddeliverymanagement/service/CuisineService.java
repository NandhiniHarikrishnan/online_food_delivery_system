package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import java.util.List;

/**
 * <p>
 * Interface that contains methods
 * create, search, update and delete a cuisine
 * </p>
 *
 * @author - jeevanantham
 * @version - 1.0
 * @since - 2022-12-10
 */
public interface CuisineService {

    /**
     * <p>
     * Create a new cuisine in database
     * <p>
     * @param cuisineDTO The cuisine object that you want to create.
     * @return A CuisineDTO object.
     * @throws FoodDeliveryManagementException - when the cuisineId is null, throw custom
     *                            exception to controller
     */
    CuisineDTO createCuisine(CuisineDTO cuisineDTO) throws FoodDeliveryManagementException;

    /**
     * <p>
     * This function returns a list of all the cuisines available in the system
     * <p>
     *
     * @return A list of CuisineDTO objects.
     * @throws FoodDeliveryManagementException - when the map objects is null, throw custom
     *                            exception to controller
     */
    List<CuisineDTO> getCuisines() throws FoodDeliveryManagementException;

    /**
     * <p>
     * This function returns a CuisineDTO object with the given id
     * <p>
     *
     * @param id The id of the cuisine to be fetched.
     * @return CuisineDTO
     * @throws FoodDeliveryManagementException - when the id is null, throw custom
     *                            exception to controller
     */
    CuisineDTO getCuisineById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * Update a cuisine by id
     * <p>
     *
     * @param cuisineDTO This is the cuisine object that you want to update.
     * @param id         The id of the cuisine to be updated.
     * @return CuisineDTO
     * @throws FoodDeliveryManagementException- when the id is null, throw custom
     *                            exception to controller
     */
    CuisineDTO updateCuisineById(CuisineDTO cuisineDTO, int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * This function deletes a cuisine from the database based on the cuisine id
     * <p>
     *
     * @param id The id of the cuisine to be deleted.
     * @return String
     * @throws FoodDeliveryManagementException - when the id is null, throw custom
     *                               exception to controller
     */
    String deleteCuisineById(int id) throws FoodDeliveryManagementException;
}
