package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Food;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {
        private int id;
        private float price;
        private int quantity;
        private FoodDTO food;

}
