package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {
    private int id;
    private float price;
    private int quantity;
}
