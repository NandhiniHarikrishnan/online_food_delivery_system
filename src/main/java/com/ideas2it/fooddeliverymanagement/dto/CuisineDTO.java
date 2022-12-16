package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
/**
 * A DTO class for Cuisine.
 *
 * @author Jeevanantham
 * @version 1.0 13-DEC-2022
 */
public class CuisineDTO {
    private int id;
    private String code;
    private String name;
    private List<RestaurantDTO> restaurantsDTO;
}