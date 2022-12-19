package com.ideas2it.fooddeliverymanagement.model;

import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurant_food")
//@SQLDelete(sql = "update restaurant_food set is_deleted = 1 where id =?")
@Where(clause = "is_deleted = false")
public class RestaurantFood extends BaseModel {

    @ManyToOne
    private Restaurant restaurant;

    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
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

    @Override
    public String toString() {
        return "RestaurantFood{" +
                "food=" + food +
                ", price=" + price +
                '}';
    }
}
