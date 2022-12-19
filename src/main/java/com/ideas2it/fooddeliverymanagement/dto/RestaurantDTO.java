package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotNull(message = "Restaurant name should not be null or empty")
    @Pattern(regexp = "[^\\s][0-9a-zA-Z\\s]*", message = "only alphanumerics and space are allowed")
    private String name;
    private List<RestaurantFoodDTO> restaurantFoods;
    private List<AddressDTO> addresses;
    private CuisineDTO cuisine;
}