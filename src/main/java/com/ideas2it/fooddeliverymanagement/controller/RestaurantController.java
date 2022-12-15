package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantDTO>> getRestaurants() throws FoodDeliveryManagementException {
        return new ResponseEntity<>(restaurantService.getRestaurants(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(
            @PathVariable int id) throws FoodDeliveryManagementException{
        return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurantById(
             @RequestBody RestaurantDTO restaurantDTO,@PathVariable int id) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(restaurantService.updateRestaurantById(restaurantDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestaurantDTO> deleteRestaurantById(@PathVariable int id) {
        return new ResponseEntity<>(restaurantService.deleteRestaurantById(id), HttpStatus.OK);
    }
}
