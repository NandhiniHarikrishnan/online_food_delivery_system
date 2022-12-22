/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * It is a restaurant detail DTO that contains fields which is shown to the user
 * in the formatted manner for the restaurant entity.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Getter
@Setter
@NoArgsConstructor
public class RestaurantDetailDTO {
    private int id;

    private String name;

    private List<FoodDTO> foods;

    private List<AddressDTO> addresses;

    private CuisineDTO cuisine;
}
