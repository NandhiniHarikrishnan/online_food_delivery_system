package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


/**
 * The Address class is an entity class that has a primary key field named addressId.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseModel{

    @NotNull
    private String plotNumber;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String district;

    @NotNull
    private String state;

    @NotNull
    private long pinCode;

    @NotNull
    private long phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
