package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.CuisineDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.CuisineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * > This class is a controller that handles requests for the `/cuisines` endpoint
 */
@RestController
@RequestMapping("/cuisine")
public class CuisineController {
    @Autowired
    CuisineService cuisineService;

    @PostMapping("/")
    public ResponseEntity<CuisineDTO> addCuisine(@RequestBody CuisineDTO cuisineDTO) {
        return new ResponseEntity<>(cuisineService.createCuisine(cuisineDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CuisineDTO>> getCuisines() throws FoodDeliveryManagementException {
        return new ResponseEntity<>(cuisineService.getCuisines(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CuisineDTO> getCuisineById(@PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(cuisineService.getCuisineById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuisineDTO> updateCuisineById(
            @RequestBody CuisineDTO cuisineDTO, @PathVariable int id) throws FoodDeliveryManagementException{
        return new ResponseEntity<>(cuisineService.updateCuisineById(cuisineDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCuisineById(
            @PathVariable int id) throws FoodDeliveryManagementException {
     return new ResponseEntity<>(cuisineService.deleteCuisineById(id), HttpStatus.OK);
    }
}