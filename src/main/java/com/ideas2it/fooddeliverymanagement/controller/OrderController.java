package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Food Delivery Application
 * Used to assign order and assign delivery boy.
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
     * Used to assign order for customer.
     * get customer id from user table and assign the order for specified customer
     *
     * @param orderDTO order details from user
     * @return placed order with id
     * @throws FoodDeliveryManagementException
     */
    @PutMapping("/{customerId}")
    public ResponseEntity<OrderDTO> assignOrders(@RequestBody OrderDTO orderDTO,
                                               @PathVariable int customerId) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(orderService.assignOrder(orderDTO,customerId),HttpStatus.CREATED);
    }

    /**
     * Used to show order detail by order id
     *
     * @param orderId get order detail
     * @return order detail for mentioned id
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/{id}")
    public OrderDTO getOrderDetails(@PathVariable("id") int orderId) throws FoodDeliveryManagementException {
        return orderService.getOrderDetails(orderId);
    }

    /**
     * assignDelivery method used to assign delivery boy by pass the order id.
     * check the available delivery list who is available then assign order.
     *
     * @param orderId to assign delivery boy for this order
     * @return assigned delivery boy id
     * @throws FoodDeliveryManagementException
     */
    @PutMapping("/assignDelivery/{id}")
    public OrderDTO assignDelivery(@PathVariable("id") int orderId) throws FoodDeliveryManagementException {
        return orderService.assignDelivery(orderId);
    }
}
