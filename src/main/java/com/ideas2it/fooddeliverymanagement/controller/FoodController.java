package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * whenever a search a food and also
     * food details
     * </p>
     *
     * @param value contains information about food table it will search
     * @return  updated food details
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @GetMapping("/search/{value}")
    public ResponseEntity<List<FoodDTO>> searchFoods(@PathVariable("value") String value) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(foodService.searchFood(value), HttpStatus.OK);
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
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(foodService.getFoodById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * whenever a admin edit a food and also
     * food details
     * </p>
     *
     * @param foodDTO contains information about food
     * @param id  food id to be update
     * @return  updated food details
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFoodById(
            @RequestBody FoodDTO foodDTO, @PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(foodService.updateFoodById(foodDTO, id), HttpStatus.OK);
    }

    /**
     * <p>
     * whenever a user wants to use cancel the food
     * </p>
     *
     * @param id  soft delete rating for the food
     * @return String given delete msg
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodById(
            @PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(foodService.deleteFoodById(id), HttpStatus.OK);
    }
}
