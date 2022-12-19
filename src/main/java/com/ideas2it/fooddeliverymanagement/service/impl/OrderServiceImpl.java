package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.OrderMapper;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.*;
import com.ideas2it.fooddeliverymanagement.repository.OrderRepository;
import com.ideas2it.fooddeliverymanagement.repository.RoleRepository;
import com.ideas2it.fooddeliverymanagement.service.OrderService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserServiceImpl userService, RoleRepository roleRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public OrderDTO assignOrder(OrderDTO orderDTO, int customerId) throws FoodDeliveryManagementException {
        Order order = new Order();
        UserMapper userMapper = new UserMapper();//demo try because usermapper non static.
        OrderDetail orderDetail = new OrderDetail();
        RestaurantFood restaurantFood = new RestaurantFood();
        order.setUser(userMapper.convertToUser(userService.getUser(customerId)));
        orderDetail.setPrice(orderDetail.getQuantity() *
                restaurantFood.getPrice());
        return OrderMapper.convertOrderDTO(orderRepository.save(OrderMapper.convertOrder(orderDTO)));
    }

    @Override
    public OrderDTO DisplayOrderDetailsById(int orderId)throws FoodDeliveryManagementException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new FoodDeliveryManagementException("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return OrderMapper.convertOrderDTO(order.get());
    }

    @Override
    public OrderDTO assignDelivery(int orderId) throws FoodDeliveryManagementException {
        Role deliveryRole = null;
        List<User> deliveryUsers;
        byte count = 0;

        Order order = orderRepository.findById(orderId).get();
        List<Role> roles = roleRepository.findAll();

        for(Role role : roles) {
            if(role.getName().equals("delivery")) {
                deliveryRole = role;
            }
        }
        for (User user : deliveryRole.getUsers()) {
            if (count == 0 && user.getStatus().equals("Un_assigned")) {
                user.setStatus("Assigned");
                order.setUser(user);
                count++;
            }
        }

        if (count == 0) {
            throw new FoodDeliveryManagementException("Not_Acceptable", HttpStatus.NOT_ACCEPTABLE);
        }
        return OrderMapper.convertOrderDTO(orderRepository.save(order));
    }
}