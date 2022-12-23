package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * OrderDetail model
 * Used to store the orderDetails.
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
@Entity
@Getter
@Setter
public class OrderDetail extends BaseModel {

    private float price;
    private int quantity;
    @ManyToOne
    private Food food;
}
