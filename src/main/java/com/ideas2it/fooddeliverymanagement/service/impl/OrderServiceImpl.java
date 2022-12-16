package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.mapper.OrderMapper;
import com.ideas2it.fooddeliverymanagement.repository.OrderRepository;
import com.ideas2it.fooddeliverymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void assignOrder(OrderDTO orderDTO) {
        orderRepository.save(orderMapper.convertOrder(orderDTO));
    }

    @Override
    public OrderDTO DisplayOrderDetailsById(int orderId) {
        return orderMapper.convertOrderDTO(orderRepository.findById(orderId).get());
    }
}