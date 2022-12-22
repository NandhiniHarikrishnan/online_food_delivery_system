/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.util.exception;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * Custom exception of Food Delivery Management application contains status code.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0
 * @since - 2022-12-14
 */
public class FoodDeliveryManagementException extends Exception {

    private HttpStatus status;

    public FoodDeliveryManagementException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
