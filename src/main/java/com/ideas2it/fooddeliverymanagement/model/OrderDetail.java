package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderDetail extends BaseModel {
    @NotNull
    private float price;

    private int quantity;
    @OneToMany(targetEntity = Food.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id",referencedColumnName = "id")
    private List<Food> foods;
}
