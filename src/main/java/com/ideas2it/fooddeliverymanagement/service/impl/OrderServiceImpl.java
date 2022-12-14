package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;

import java.util.List;

public interface OrderServiceImpl {
    public void assignOrder(OrderDTO orderDTO);
    public OrderDTO DisplayOrderDetailsById(int orderId);
}
