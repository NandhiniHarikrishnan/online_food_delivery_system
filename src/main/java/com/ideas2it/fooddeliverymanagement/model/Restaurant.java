package com.ideas2it.fooddeliverymanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Restaurant extends BaseModel {

    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RestaurantFood> restaurantFoods;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private List<Address> addresses;

    @ManyToOne(fetch = FetchType.LAZY)
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
        this.restaurantFoods = restaurantFoods;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }
}
