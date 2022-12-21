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
    OrderDTO assignOrder(OrderDTO orderDTO,int customerId) throws FoodDeliveryManagementException;

    OrderDTO getOrderDetails(int orderId) throws FoodDeliveryManagementException;

    OrderDTO assignDelivery(int orderId) throws FoodDeliveryManagementException;
}
