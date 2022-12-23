/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Food Delivery Application
 * Used to place the order by customer and assign delivery boy by admin.
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */


@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Used to assign order for customer,by pass the values of customer id,restaurant id and food id in json
     * check the customer id,restaurant id and food id from respective table if present assign the order for specified customer
     * else it throws 404 not found exception in json,once order is placed json will show 200 ok http status code.
     *
     * @param orderDTO order details from user
     * @return placed order with id
     * @throws FoodDeliveryManagementException
     */
    @PostMapping("/")
    public OrderDTO assignOrders(@RequestBody OrderDTO orderDTO) throws FoodDeliveryManagementException {
        return orderService.assignOrder(orderDTO);
    }

    /**
     * Used to show order detail by order id,once order is placed then based on order id customer can
     * show the order details,in case of order id is not there it throws 404 not found exception in json
     *
     * @param id get order detail
     * @return order detail for mentioned id
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/{id}")
    public OrderDTO getOrderDetails(@PathVariable int id) throws FoodDeliveryManagementException {
        return orderService.getOrderDetails(id);
    }

    /**
     * Used to assign delivery boy by pass the order id
     * check the available delivery boy list who is available
     * then assign delivery boy for that order
     * it can be done by only admin role.
     *
     * @param id to assign delivery boy for this order
     * @return assigned delivery boy id
     * @throws FoodDeliveryManagementException
     */
    @PutMapping("/{id}")
    public OrderDTO assignDeliveryPerson(@PathVariable int id) throws FoodDeliveryManagementException {
        return orderService.assignDeliveryPerson(id);
    }
}
