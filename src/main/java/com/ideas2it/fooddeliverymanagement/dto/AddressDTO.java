package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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

    private int id;

    private String plotNumber;

    private String street;

    private String city;

    private String district;

    private String state;

    private long pinCode;

    private long phoneNumber;

}
