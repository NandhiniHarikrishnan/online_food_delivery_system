package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
