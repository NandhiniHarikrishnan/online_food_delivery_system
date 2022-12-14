package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO extends BaseModel {
    private int id;
    private float price;
    private int quantity;
}
