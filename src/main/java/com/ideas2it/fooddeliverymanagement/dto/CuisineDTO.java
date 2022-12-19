package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * A DTO class for Cuisine.
 *
 * @author Jeevanantham
 * @version 1.0 13-DEC-2022
 */
@Getter
@Setter
@NoArgsConstructor
public class CuisineDTO {
    private int id;
    private String code;
    private String name;
    private List<RestaurantDTO> restaurants;
}