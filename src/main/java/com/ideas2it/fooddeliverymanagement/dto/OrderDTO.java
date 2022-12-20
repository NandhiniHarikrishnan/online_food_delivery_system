package com.ideas2it.fooddeliverymanagement.dto;

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
    private UserDTO customer;
    private RestaurantDTO restaurant;
    private float totalPrice;
    private List<OrderDetailDTO> orderDetail;
}
