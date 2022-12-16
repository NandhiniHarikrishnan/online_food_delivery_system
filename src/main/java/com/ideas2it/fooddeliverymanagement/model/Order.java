package com.ideas2it.fooddeliverymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetails;
}




