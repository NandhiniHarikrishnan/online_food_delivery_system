package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.service.RestaurantFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private RestaurantFoodService restaurantFoodService;

    @PostMapping("/")
    public ResponseEntity<RestaurantFoodDTO> addFood(@RequestBody RestaurantFoodDTO restaurantFoodDTO ) {
        return new ResponseEntity<> (restaurantFoodService.addFood(restaurantFoodDTO), HttpStatus.CREATED);
    }

}
