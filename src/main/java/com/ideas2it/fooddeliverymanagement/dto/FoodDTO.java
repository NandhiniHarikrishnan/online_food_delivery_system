package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * It is a food DTO that contains fields which is shown to the user
 * for the food entity.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Getter
@Setter
@NoArgsConstructor
public class FoodDTO {
    private int id;
    private String name;
    private String description;
    private boolean isAvailable;
    private String weight;
    private List<RestaurantFoodDTO> restaurantFoods;
    private List<OrderDetailDTO> orderDetails;
    private CategoryDTO category;
    private float price;
}
