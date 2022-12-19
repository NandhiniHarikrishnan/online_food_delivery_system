package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;
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
    @NotNull(message = Constants.NOT_NULL_OR_EMPTY_NAME)
    @Pattern(regexp = Constants.REGEX_FOR_RESTAURANT, message = Constants.ONLY_ALPHANUMERIC_SPACE)
    private String name;
    private List<RestaurantFoodDTO> restaurantFoods;
    @NotNull(message = Constants.NOT_NULL_OR_EMPTY_ADDRESS)
    private List<AddressDTO> addresses;
    private CuisineDTO cuisine;
}