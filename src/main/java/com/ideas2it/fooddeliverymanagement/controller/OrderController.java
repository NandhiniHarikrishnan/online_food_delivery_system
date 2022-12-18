package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PutMapping("/assignOrder/{customerId}")
    public ResponseEntity<OrderDTO> assignOrders(@RequestBody OrderDTO orderDTO,
                                               @PathVariable int customerId) throws FoodDeliveryManagementException {
        orderService.assignOrder(orderDTO,customerId);
        return new ResponseEntity<>(orderService.assignOrder(orderDTO,customerId),HttpStatus.CREATED);
    }

    @GetMapping("/orderDetails/{id}")
    public OrderDTO getOrderDetails(@PathVariable("id") int orderId) throws FoodDeliveryManagementException {
        return orderService.DisplayOrderDetailsById(orderId);
    }

    @PutMapping("/assignDelivery/{id}")
    public OrderDTO assignDelivery(@PathVariable("id") int orderId) {
        return orderService.assignDelivery(orderId);
    }
}
