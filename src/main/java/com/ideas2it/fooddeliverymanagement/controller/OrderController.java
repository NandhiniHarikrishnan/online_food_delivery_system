package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.fooddeliverymanagement.repository.OrderRepository;
import com.ideas2it.fooddeliverymanagement.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PutMapping("/assignOrder")
    public ResponseEntity<String> assignOrders(@RequestBody OrderDTO orderDTO) {
        orderServiceImpl.assignOrder(orderDTO);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    public void assignOrders(@RequestBody OrderDTO orderDTO) {
        orderServiceImpl.assignOrder(orderDTO);
    }

    @GetMapping("/orderDetails/{id}")
    public OrderDTO getOrderDetails(@PathVariable("id") int orderId) {
        return orderServiceImpl.DisplayOrderDetailsById(orderId);
    }
}
