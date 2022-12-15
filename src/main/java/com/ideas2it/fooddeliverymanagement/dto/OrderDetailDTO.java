package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.BaseModel;
import com.ideas2it.fooddeliverymanagement.model.Food;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class OrderDetailDTO extends BaseModel {
    private int id;
    private float price;
    private int quantity;
    private Food food;
}
