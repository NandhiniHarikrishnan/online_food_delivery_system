package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * It's a DTO class that contains all the fields of the Address entity
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {
    private int addressId;

    private String plotNumber;

    private String streetName;

    private String cityName;

    private String districtName;

    private String stateName;

    private String pinCode;

    private String phoneNumber;

}
