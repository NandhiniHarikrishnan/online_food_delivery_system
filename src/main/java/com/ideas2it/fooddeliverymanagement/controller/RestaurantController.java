package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/")
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }
}
