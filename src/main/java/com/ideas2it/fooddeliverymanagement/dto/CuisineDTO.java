/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * A DTO class for Cuisine.
 *
 * @author Jeevanantham
 * @version 1.0
 * @Since 15-DEC-2022
 */
@Getter
@Setter
@NoArgsConstructor
public class CuisineDTO {
    private int id;
    private String code;
    @NotNull(message = Constants.NOT_NULL_OR_EMPTY_NAME)
    @Pattern(regexp = Constants.REGEX_FOR_CUISINE, message = Constants.ONLY_ALPHABETS_SPACE)
    private String name;
    private String restaurant;
    private List<RestaurantDTO> restaurants;
}