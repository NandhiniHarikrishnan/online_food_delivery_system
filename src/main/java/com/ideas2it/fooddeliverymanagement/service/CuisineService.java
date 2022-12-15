package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import java.util.List;

public interface CuisineService {
    CuisineDTO createCuisine(CuisineDTO cuisineDTO);

    List<CuisineDTO> getCuisines() throws FoodDeliveryManagementException;

    CuisineDTO getCuisineById(int id) throws FoodDeliveryManagementException;

    CuisineDTO updateCuisineById(CuisineDTO cuisineDTO, int id) throws FoodDeliveryManagementException;

    String deleteCuisineById(int id) throws FoodDeliveryManagementException;
}
