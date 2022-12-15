package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;

public interface OrderService {
    public void assignOrder(OrderDTO orderDTO);
    public OrderDTO DisplayOrderDetailsById(int orderId);
}