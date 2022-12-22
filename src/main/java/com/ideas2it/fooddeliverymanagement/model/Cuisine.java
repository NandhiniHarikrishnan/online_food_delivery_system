/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The Cuisine which has a field like name, code and restaurants.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
@Where(clause = "is_deleted = false")
public class Cuisine extends BaseModel {

    @NotNull
    @Column(columnDefinition="varchar(20) unique")
    private String code;

    @NotNull
    @Column(columnDefinition="varchar(20) unique")
    private String name;

    @OneToMany(mappedBy = "cuisine", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    public Cuisine() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        if(null != restaurants) {
            restaurants.forEach(restaurant -> restaurant.setCuisine(this));
            this.restaurants = restaurants;
        }
    }
}

