/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;

/**
 * OrderService is used to assign order and assign delivery boy
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
public interface OrderService {

    /**
     * Customer is signed then customer can place the order
     * select the restaurant then choose the multiple dishes
     * once selected it shows total price of order to the customer.
     *
     * @param orderDTO pass customer id and order dto to assign order
     * @return assigned order detail
     * @throws FoodDeliveryManagementException if customer id not there
     */
    OrderDTO assignOrder(OrderDTO orderDTO) throws FoodDeliveryManagementException;

    /**
     * view order details with specified order id once order is placed then view the
     * order details.
     *
     * @param orderId pass id to get order details
     * @return order detail for specified id
     * @throws FoodDeliveryManagementException if order id not there
     */
    OrderDTO getOrderDetails(int orderId) throws FoodDeliveryManagementException;

    /**
     * Used to assign delivery boy
     * check the delivery boy available status then assign the delivery boy for that order.
     *
     * @param orderId assign order by order id
     * @return assigned delivery id
     * @throws FoodDeliveryManagementException if order id not match
     */
    OrderDTO assignDeliveryPerson(int orderId) throws FoodDeliveryManagementException;
}
