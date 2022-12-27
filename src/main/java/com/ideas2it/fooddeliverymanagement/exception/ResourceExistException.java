/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.exception;

/**
 * <p>
 * Custom exception of Food Delivery Management application contains status code.
 * </p>
 *
 * @author Naganandhini
 * @version 1.0
 * @since - 2022-12-22
 */
public class ResourceExistException extends Exception {

    public ResourceExistException(String message) {
        super(message);
    }
}


