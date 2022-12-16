package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.dto.OrderDetailDTO;
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
    private String weight;
    private List<RestaurantFoodDTO> restaurantFoods;
    private List<OrderDetailDTO> orderDetails;
    private CategoryDTO category;
}
