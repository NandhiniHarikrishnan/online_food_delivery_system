/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO class for orderDetail
 * to pass object store and retrieve by controller.
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
@Getter
@Setter
public class OrderDetailDTO {
        private int id;
        private float price;
        private int quantity;
        private FoodDTO food;

}
