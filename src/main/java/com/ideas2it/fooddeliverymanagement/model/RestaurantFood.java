package com.ideas2it.fooddeliverymanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurant_food")
public class RestaurantFood extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Food food;

    @NotNull
    private float price;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
