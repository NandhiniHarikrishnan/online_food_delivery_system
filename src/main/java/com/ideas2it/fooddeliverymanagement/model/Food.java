package com.ideas2it.fooddeliverymanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Food extends BaseModel {

    @NotNull
    private String name;

    private String description;

    @NotNull
    @Column(name = "is_available", columnDefinition = "tinyint(1) default true")
    private boolean isAvailable;

    @NotNull
    private String weight;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private List<RestaurantFood> restaurantFoods;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private List<OrderDetail> orderDetails;

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<RestaurantFood> getRestaurantFoods() {
        return restaurantFoods;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setRestaurantFoods(List<RestaurantFood> restaurantFoods) {
        this.restaurantFoods = restaurantFoods;
    }


}
