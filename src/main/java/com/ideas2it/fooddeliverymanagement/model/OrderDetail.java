package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderDetail extends BaseModel {
    private float price;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;
}
