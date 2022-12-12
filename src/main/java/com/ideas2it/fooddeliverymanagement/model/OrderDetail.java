package com.ideas2it.fooddeliverymanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDetail {
    @Id
    private int id;
    private float price;
    private String quantity;
}
