package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * A DTO class for RestaurantDTO.
 *
 * @author Jeevanantham
 * @version 1.0 13-DEC-2022
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RestaurantDTO {
    private int id;
    private String name;
    private List<RestaurantFoodDTO> restaurantFoods;
    private List<AddressDTO> addresses;
    private CuisineDTO cuisine;
}