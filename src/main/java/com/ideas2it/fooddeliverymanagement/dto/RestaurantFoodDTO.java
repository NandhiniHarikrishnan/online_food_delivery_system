package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO class for RestaurantFoodDTO.
 *
 * @author Jeevanantham
 * @version 1.0 13-DEC-2022
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RestaurantFoodDTO {
    private int id;
    private RestaurantDTO restaurant;
    private FoodDTO food;
    private float price;
}
