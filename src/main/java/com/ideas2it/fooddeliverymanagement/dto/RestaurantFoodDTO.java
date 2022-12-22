/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO class for RestaurantFood.
 *
 * @author Jeevanantham
 * @version 1.0
 * @Since 15-DEC-2022
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
