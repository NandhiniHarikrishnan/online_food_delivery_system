package com.ideas2it.fooddeliverymanagement.exception;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * Custom exception of Food Delivery Management application
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 14-DEC-2022
 */
public class FoodDeliveryManagementException extends Exception {

    private HttpStatus status;

    public FoodDeliveryManagementException(String message, String code, HttpStatus status) {
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
