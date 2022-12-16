package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Where(clause = "is_deleted = false")
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
