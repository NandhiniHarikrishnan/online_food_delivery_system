package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CuisineDTO {
    private int id;
    private String code;
    private String name;
    private List<RestaurantDTO> restaurantDTOs;
}