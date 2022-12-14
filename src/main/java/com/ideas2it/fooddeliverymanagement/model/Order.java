package com.ideas2it.fooddeliverymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_order")
public class Order extends BaseModel {

    private String status;


    private LocalDate dateOfOrder;

    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetails;
}




