package com.ideas2it.fooddeliverymanagement.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class OrderDetail extends BaseModel {
    @NotNull
    private float price;
    @NotNull
    private int quantity;
}
