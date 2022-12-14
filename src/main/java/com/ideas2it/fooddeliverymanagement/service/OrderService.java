package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.mapper.OrderMapper;
import com.ideas2it.fooddeliverymanagement.repository.OrderRepository;
import com.ideas2it.fooddeliverymanagement.repository.UserRepository;
import com.ideas2it.fooddeliverymanagement.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderServiceImpl {
    @Autowired
    private UserRepository userRepository;
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
        return orderMapper.convertOrderDTO(orderRepository.findById(orderId)
                .orElse(null));
    }
}
