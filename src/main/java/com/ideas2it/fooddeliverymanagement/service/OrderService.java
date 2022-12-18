package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

public interface OrderService {
    OrderDTO assignOrder(OrderDTO orderDTO,int customerId) throws FoodDeliveryManagementException;
    OrderDTO DisplayOrderDetailsById(int orderId) throws FoodDeliveryManagementException;

    OrderDTO assignDelivery(int orderId) throws FoodDeliveryManagementException;
}
