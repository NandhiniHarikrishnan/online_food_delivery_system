/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.service.FoodService;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * A controller class that handles requests for the /food endpoint
 * and passes the request to the service to get results from the database
 * and then sends to the client as response
 * <p>
 *
 * @author Jeevanantham
 * @version - 1.0
 * @since - 2022-12-14
 */
@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * <p>
     * whenever a search a food and also food details
     * </p>
     *
     * @param keyword contains information about food table it will search
     * @return updated food details
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @GetMapping("/search/")
    public List<FoodDTO> searchFoods(@RequestParam String keyword) throws FoodDeliveryManagementException {
        return foodService.searchFood(keyword);
    }

    /**
     * <p>
     * To retrieve food based on the id
     * </p>
     *
     * @return food in given id
     * @throws FoodDeliveryManagementException - if the food is not found
     */
    @GetMapping("/{id}")
    public FoodDTO getFoodById(@PathVariable int id) throws FoodDeliveryManagementException {
        return foodService.getFoodById(id);
    }

    /**
     * <p>
     * whenever a admin edit a food and also food details
     * </p>
     *
     * @param foodDTO contains information about food
     * @param id      food id to be updated
     * @return updated food details
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @PutMapping("/{id}")
    public FoodDTO updateFoodById(
            @RequestBody FoodDTO foodDTO, @PathVariable int id) throws FoodDeliveryManagementException {
        return foodService.updateFoodById(foodDTO, id);
    }

    /**
     * <p>
     * whenever a user wants to use remove the food
     * </p>
     *
     * @param id soft delete rating for the food
     * @return String given delete msg
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @DeleteMapping("/{id}")
    public String deleteFoodById(
            @PathVariable int id) throws FoodDeliveryManagementException {
        return foodService.deleteFoodById(id);
    }

    /**
     * <p>
     * whenever a search a food by category
     * food details
     * </p>
     *
     * @param name contains information about food table and category table it will search
     * @return updated food details
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @GetMapping("/search-by-category/")
    public List<FoodDTO> searchFoodByCategory(@RequestParam String name) throws FoodDeliveryManagementException {
        return foodService.searchFoodByCategory(name);
    }
}
