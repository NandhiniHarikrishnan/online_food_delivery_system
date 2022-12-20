package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDetailDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.OrderMapper;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.*;
import com.ideas2it.fooddeliverymanagement.repository.OrderRepository;
import com.ideas2it.fooddeliverymanagement.repository.RoleRepository;
import com.ideas2it.fooddeliverymanagement.service.OrderService;
import com.ideas2it.fooddeliverymanagement.service.RestaurantFoodService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    private RestaurantFoodService restaurantFoodService;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserServiceImpl userService, RoleRepository roleRepository,
                            RestaurantFoodService restaurantFoodService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.restaurantFoodService = restaurantFoodService;
    }

    @Override
    public OrderDTO assignOrder(OrderDTO orderDTO, int customerId) throws FoodDeliveryManagementException {
        int restaurantId = orderDTO.getRestaurant().getId();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetail()) {
            int id = orderDetailDTO.getFood().getId();
            orderDetailDTO.setPrice(restaurantFoodService.getPrice(id, restaurantId));
            orderDetailDTOS.add(orderDetailDTO);
        }
        orderDTO.setOrderDetail(orderDetailDTOS);
        Order order = OrderMapper.convertOrder(orderDTO);
        order.setCustomer(UserMapper.convertToUser(userService.getUser(customerId)));
        return OrderMapper.convertOrderDTO(orderRepository.save(order));
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
        byte count = 0;

        Order order = orderRepository.findById(orderId).get();
        List<Role> roles = roleRepository.findAll();

        for(Role role : roles) {
            if(role.getName().equals("delivery")) {
                deliveryRole = role;
            }
        }
        for (User user : deliveryRole.getUsers()) {
            if (count == 0 && user.getStatus().equals("UnAssigned")) {
                user.setStatus("Assigned");
                order.setCustomer(user);
                count++;
            }
        }

        if (count == 0) {
            throw new FoodDeliveryManagementException("Not_Acceptable", HttpStatus.NOT_ACCEPTABLE);
        }
        return OrderMapper.convertOrderDTO(orderRepository.save(order));
    }
}
