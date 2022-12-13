package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    @NotNull
    private String plotNumber;
    @NotNull
    private String streetName;
    @NotNull
    private String city;
    @NotNull
    private String district;
    @NotNull
    private String state;
    @NotNull
    private int pinCode;
    @NotNull
    private long phoneNumber;
//    @NotNull
    private LocalDateTime createdAt;
//    @NotNull
    private LocalDateTime updatedAt;
}
