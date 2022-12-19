package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * Contains all the fields of the Address entity
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

    private int id;

    @NotBlank(message = Constants.MENTION_PLOT_NUMBER)
    private String plotNumber;

    @NotBlank(message = Constants.MENTION_STREET_NAME)
    private String street;

    @NotBlank(message = Constants.MENTION_CITY_NAME)
    private String city;

    @NotBlank(message = Constants.MENTION_DISTRICT_NAME)
    private String district;

    @NotBlank(message = Constants.MENTION_STATE_NAME)
    private String state;

    @Pattern(regexp = Constants.REGEX_FOR_PINCODE)
    private long pinCode;

    @Pattern(regexp = Constants.REGEX_FOR_PHONE_NUMBER)
    private long phoneNumber;

    private UserDTO user;

    private RestaurantDTO restaurantDTO;

}
