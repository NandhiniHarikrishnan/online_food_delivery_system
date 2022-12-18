package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.model.User;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private String status;
    private LocalDate dateOfOrder;
    private User customer;
    private Restaurant restaurant;
    private List<OrderDetailDTO> orderDetail;
}
