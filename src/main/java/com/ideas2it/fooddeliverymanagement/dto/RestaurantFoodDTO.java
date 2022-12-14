package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Food;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantFoodDTO {
    private int id;
    private RestaurantDTO restaurantDTO;
    private FoodDTO foodDTO;
    private float price;
}
