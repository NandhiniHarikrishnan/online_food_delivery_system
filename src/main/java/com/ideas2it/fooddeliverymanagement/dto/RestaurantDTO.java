package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.Cuisine;
import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDTO {
    private int id;
    private String name;
    private List<RestaurantFoodDTO> restaurantFoodDTOs;
    private List<AddressDTO> addressesDTO;
    private CuisineDTO cuisineDTO;
}