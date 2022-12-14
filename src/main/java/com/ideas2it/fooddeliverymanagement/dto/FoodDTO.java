package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Category;
import com.ideas2it.fooddeliverymanagement.model.OrderDetail;
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
    private String weight;
    private List<RestaurantFoodDTO> restaurantFoods;
    private List<OrderDetail> orderDetails;
    private CategoryDTO category;
}
