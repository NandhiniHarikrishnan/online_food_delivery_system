package com.ideas2it.fooddeliverymanagement.model;

import lombok.ToString;
import org.hibernate.annotations.Cascade;

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
@ToString
public class Food extends BaseModel {

    @NotNull
    private String name;

    private String description;

    @NotNull
    @Column(name = "is_available", columnDefinition = "tinyint(1) default true")
    private boolean isAvailable;

    @NotNull
    private String weight;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private List<RestaurantFood> restaurantFoods;

    @OneToMany(mappedBy = "food")
    //@JoinColumn(name = "food_id", referencedColumnName = "id")
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

    public List<com.ideas2it.fooddeliverymanagement.model.OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<com.ideas2it.fooddeliverymanagement.model.OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setRestaurantFoods(List<RestaurantFood> restaurantFoods) {
        this.restaurantFoods = restaurantFoods;
    }


}
