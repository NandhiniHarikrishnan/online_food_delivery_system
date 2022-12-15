package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDetailDTO;
import com.ideas2it.fooddeliverymanagement.model.Order;
import com.ideas2it.fooddeliverymanagement.model.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    public Order convertOrder(OrderDTO orderDTO) {
        Order order = new Order();
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderDetailDTO> orders = orderDTO.getOrderDetail();

        if (!orders.isEmpty()) {
            for (OrderDetailDTO orderDTOs : orders) {
                orderDetails.add(convertOrderDetail(orderDTOs));
            }
        }

        order.setStatus(orderDTO.getStatus());
        order.setDateOfOrder(orderDTO.getDateOfOrder());
        order.setOrderDetails(orderDetails);
        return order;
    }

    public OrderDetail convertOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setFood(orderDetailDTO.getFood());
        return orderDetail;
    }

    public OrderDTO convertOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        List<OrderDetail> orderDetail = order.getOrderDetails();

        if (!orderDetail.isEmpty()) {
            for (OrderDetail orders : orderDetail) {
                orderDetailDTOS.add(convertOrderDetailDTO(orders));
            }
        }
        orderDTO.setId(order.getId());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        orderDTO.setDelete(order.isDeleted());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setDateOfOrder(order.getDateOfOrder());
        orderDTO.setOrderDetail(orderDetailDTOS);
        return orderDTO;
    }

    public OrderDetailDTO convertOrderDetailDTO(OrderDetail orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId((orderDetail.getId()));
        orderDetailDTO.setCreatedAt(orderDetail.getCreatedAt());
        orderDetailDTO.setUpdatedAt(orderDetail.getUpdatedAt());
        orderDetailDTO.setDelete(orderDetail.isDeleted());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        return orderDetailDTO;
    }
}
