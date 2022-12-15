package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Component
public class OrderDetail extends BaseModel {

    private float price;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;
}
