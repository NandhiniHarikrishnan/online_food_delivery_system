package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FoodDTO {
    private int id;
    private String name;
    private String description;
    private boolean isAvailable;
    private float weight;
    private List<RestaurantFood> restaurantFoods;
}
