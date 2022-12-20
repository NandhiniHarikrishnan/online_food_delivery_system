package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;

import java.util.List;

public interface CuisineService {

    /**
     * Create a new cuisine
     *
     * @param cuisineDTO The cuisine object that you want to create.
     * @return A CuisineDTO object.
     */
    CuisineDTO createCuisine(CuisineDTO cuisineDTO);

    /**
     * > This function returns a list of all the cuisines available in the system
     *
     * @return A list of CuisineDTO objects.
     */
    List<CuisineDTO> getCuisines() throws FoodDeliveryManagementException;

    /**
     * This function returns a CuisineDTO object with the given id
     *
     * @param id The id of the cuisine to be fetched.
     * @return CuisineDTO
     */
    CuisineDTO getCuisineById(int id) throws FoodDeliveryManagementException;

    /**
     * Update a cuisine by id
     *
     * @param cuisineDTO This is the cuisine object that you want to update.
     * @param id The id of the cuisine to be updated.
     * @return CuisineDTO
     */
    CuisineDTO updateCuisineById(CuisineDTO cuisineDTO, int id) throws FoodDeliveryManagementException;

    /**
     * This function deletes a cuisine from the database based on the cuisine id
     *
     * @param id The id of the cuisine to be deleted.
     * @return String
     */
    String deleteCuisineById(int id) throws FoodDeliveryManagementException;
}
