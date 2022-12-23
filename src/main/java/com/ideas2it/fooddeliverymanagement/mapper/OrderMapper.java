/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDetailDTO;
import com.ideas2it.fooddeliverymanagement.model.*;
import com.ideas2it.fooddeliverymanagement.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderMapper used to convert dto to entity
 * and entity to dto.
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
public class OrderMapper {
    /**
     * Used to convert orderDTO to Order(model)
     * convertOrderDetail it calls to convert orderDetailDTO to OrderDetail(model)
     * convertRestaurant it calls from RestaurantMapper class for convert restaurantDTO to Restaurant.
     *
     * @param orderDTO pass values for assign order.
     * @return it returns the assigned order.
     */
    public static Order convertOrder(OrderDTO orderDTO) {
        Order order = new Order();
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderDetailDTO> orderDetailDTOS = orderDTO.getOrderDetail();

        if (!orderDetailDTOS.isEmpty()) {
            for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
                orderDetails.add(convertOrderDetail(orderDetailDTO));
            }
        }

        order.setStatus(Constants.ORDERED);
        order.setCustomer(UserMapper.convertToUser(orderDTO.getCustomer()));
        order.setDateOfOrder(orderDTO.getDateOfOrder());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setRestaurant(RestaurantMapper.convertRestaurant(orderDTO.getRestaurant()));
        order.setOrderDetails(orderDetails);
        return order;
    }

    public static OrderDetail convertOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setFood(FoodMapper.convertIntoEntity(orderDetailDTO.getFood()));
        return orderDetail;
    }

    /**
     * get order into orderDTO
     * to view order detail by dto format.
     *
     * @param order get object from order
     * @return orderDTO
     */
    public static OrderDTO convertOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        List<OrderDetail> orderDetail = order.getOrderDetails();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCustomer(UserMapper.convertUserDTO(order.getCustomer()));
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setDateOfOrder(order.getDateOfOrder());
        if (!orderDetail.isEmpty()) {
            for (OrderDetail orders : orderDetail) {
                orderDetailDTOS.add(convertOrderDetailDTO(orders));
            }
        }
        orderDTO.setOrderDetail(orderDetailDTOS);
        orderDTO.setRestaurant(RestaurantMapper.convertRestaurantDTO(order.getRestaurant()));
        return orderDTO;
    }

    public static OrderDetailDTO convertOrderDetailDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId((orderDetail.getId()));
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setFood(FoodMapper.convertIntoDTO(orderDetail.getFood()));
        return orderDetailDTO;
    }
}