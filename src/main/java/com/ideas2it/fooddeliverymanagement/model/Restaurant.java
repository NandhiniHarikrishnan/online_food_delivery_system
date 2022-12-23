/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The restaurant which has a field like name, cuisine, addresses and food details.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
@SQLDelete(sql = "update restaurant set is_deleted = 1 where id =?")
@Where(clause = "is_deleted = false")
public class Restaurant extends BaseModel {

    @NotNull
    private String name;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<RestaurantFood> restaurantFoods;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<Address> addresses;

    @ManyToOne
    private Cuisine cuisine;

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantFood> getRestaurantFoods() {
        return restaurantFoods;
    }

    public void setRestaurantFoods(List<RestaurantFood> restaurantFoods) {
        if (null != restaurantFoods) {
            restaurantFoods.forEach(restaurantFood -> restaurantFood.setRestaurant(this));
            this.restaurantFoods = restaurantFoods;
        }
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        addresses.forEach(address -> address.setRestaurant(this));
        this.addresses = addresses;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }
}
