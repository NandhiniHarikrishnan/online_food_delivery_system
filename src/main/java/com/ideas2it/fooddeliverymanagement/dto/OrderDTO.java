package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO class for Order
 * to assign order by pass object to model
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
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
