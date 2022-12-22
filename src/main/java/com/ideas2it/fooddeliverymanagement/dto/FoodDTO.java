/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotNull(message = Constants.NOT_NULL_OR_EMPTY_NAME)
    @Pattern(regexp = Constants.REGEX_FOR_FOOD, message = Constants.ONLY_ALPHANUMERIC_SPACE)
    private String name;
    @Pattern(regexp = Constants.REGEX_FOR_FOOD)
    private String description;
    private boolean isAvailable;
    private String weight;
    private List<RestaurantFoodDTO> restaurantFoods;
    private List<OrderDetailDTO> orderDetails;
    private CategoryDTO category;
    private float price;
}
