package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class OrderDetail {
    @Id
    private int id;
    private float price;
    private int quantity;
}
