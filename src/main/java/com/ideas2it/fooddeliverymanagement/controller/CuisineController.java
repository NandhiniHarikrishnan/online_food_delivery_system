package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.CuisineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller that handles requests for the `/cuisines` endpoint
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
    public ResponseEntity<CuisineDTO> addCuisine(@RequestBody CuisineDTO cuisineDTO) {
        return new ResponseEntity<>(cuisineService.createCuisine(cuisineDTO), HttpStatus.CREATED);
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
    public ResponseEntity<List<CuisineDTO>> getCuisines() throws FoodDeliveryManagementException {
        return new ResponseEntity<>(cuisineService.getCuisines(), HttpStatus.OK);
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
    public ResponseEntity<CuisineDTO> getCuisineById(@PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(cuisineService.getCuisineById(id), HttpStatus.OK);
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
     */
    @PutMapping("/{id}")
    public ResponseEntity<CuisineDTO> updateCuisineById(
            @RequestBody CuisineDTO cuisineDTO, @PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(cuisineService.updateCuisineById(cuisineDTO, id), HttpStatus.OK);
    }

    /**
     * <p>
     * whenever a user wants to use cancel the cuisine
     * </p>
     *
     * @param id  soft delete rating for the cuisine
     * @return String given delete msg
     *
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCuisineById(
            @PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(cuisineService.deleteCuisineById(id), HttpStatus.OK);
    }
}