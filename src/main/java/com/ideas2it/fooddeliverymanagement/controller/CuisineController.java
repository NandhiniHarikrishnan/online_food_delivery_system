/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.CuisineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * This class is a controller that handles requests for the `/cuisines` endpoint
 * and passes the request to the service to get results from the database
 * and then sends to the client as response
 *  <p>
 *
 * @author Jeevanantham
 * @version - 1.0
 * @since - 2022-12-14
 */
@RestController
@RequestMapping("/cuisine")
public class CuisineController {
    private final CuisineService cuisineService;

    @Autowired   
    public CuisineController(CuisineService cuisineService) {
        this.cuisineService = cuisineService;
    }

    /**
     * <p>
     * whenever a admin add a cuisine and also
     * cuisine details
     * </p>
     *
     * @param cuisineDTO cuisine details of a admin
     * @return cuisineDTO details
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @PostMapping("/")
    public CuisineDTO addCuisine(@RequestBody CuisineDTO cuisineDTO) throws FoodDeliveryManagementException{
        return cuisineService.createCuisine(cuisineDTO);
    }

    /**
     * <p>
     * To retrieve all cuisines irrespective of id
     * </p>
     *
     * @return set of all cuisines
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @GetMapping("/")
    public List<CuisineDTO> getCuisines() throws FoodDeliveryManagementException {
        return cuisineService.getCuisines();
    }

    /**
     * <p>
     * To retrieve cuisine based on the id
     * </p>
     *
     * @return cuisine in given id
     * @throws FoodDeliveryManagementException - if the cuisine is not found
     */
    @GetMapping("/{id}")
    public CuisineDTO getCuisineById(@PathVariable int id) throws FoodDeliveryManagementException {
        return cuisineService.getCuisineById(id);
    }

    /**
     * <p>
     * whenever a admin edit a cuisine and also
     * cuisine details
     * </p>
     *
     * @param cuisineDTO contains information about cuisine
     * @param id  cuisine id to be update
     * @return  updated cuisine details
     * @throws FoodDeliveryManagementException - if the cuisine is not found
     */
    @PutMapping("/{id}")
    public CuisineDTO updateCuisineById(
            @RequestBody CuisineDTO cuisineDTO, @PathVariable int id) throws FoodDeliveryManagementException {
        return cuisineService.updateCuisineById(cuisineDTO, id);
    }

    /**
     * <p>
     * whenever a user wants to use cancel the cuisine
     * </p>
     *
     * @param id  soft delete rating for the cuisine
     * @return String given delete msg
     * @throws FoodDeliveryManagementException - if the cuisine is not found
     */
    @DeleteMapping("/{id}")
    public String deleteCuisineById(
            @PathVariable int id) throws FoodDeliveryManagementException {
        return cuisineService.deleteCuisineById(id);
    }
}