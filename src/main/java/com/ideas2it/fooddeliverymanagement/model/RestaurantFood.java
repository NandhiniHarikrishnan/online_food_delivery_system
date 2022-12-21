package com.ideas2it.fooddeliverymanagement.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The restaurantFood contains common attributes for restaurant and food
 * such as price.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
@Table(name = "restaurant_food")
@SQLDelete(sql = "update restaurant_food set is_deleted = 1 where id =?")
@Where(clause = "is_deleted = false")
public class RestaurantFood extends BaseModel {

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Restaurant restaurant;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
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
