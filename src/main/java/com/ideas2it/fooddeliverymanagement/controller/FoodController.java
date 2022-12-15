package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.mapper.RestaurantFoodMapper;
import com.ideas2it.fooddeliverymanagement.model.Food;
import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import com.ideas2it.fooddeliverymanagement.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantFoodMapper restaurantFoodMapper;

    @PostMapping("/")
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        return new ResponseEntity<> (foodService.addFood(food), HttpStatus.CREATED);
    }

}
