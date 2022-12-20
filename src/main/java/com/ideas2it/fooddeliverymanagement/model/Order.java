package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "food_order")
public class Order extends BaseModel {
    private String status;
    private LocalDate dateOfOrder;
    private int deliveryId;
    private float totalPrice;
    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;
}




