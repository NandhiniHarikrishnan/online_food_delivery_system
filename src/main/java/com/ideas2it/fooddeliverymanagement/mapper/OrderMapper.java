package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDetailDTO;
import com.ideas2it.fooddeliverymanagement.model.Order;
import com.ideas2it.fooddeliverymanagement.model.OrderDetail;

import java.util.List;

public class OrderMapper {
    public Order convertOrder(OrderDTO orderDTO) {
        Order order = new Order();
        List<OrderDetail> orderDetails = null;
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

        return orderDetail;
    }

    public OrderDTO convertOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetailDTO> orderDetailDTO = null;
        List<OrderDetail> orderDetail = order.getOrderDetails();

        if (!orderDetail.isEmpty()) {
            for (OrderDetail orders : orderDetail) {
                orderDetailDTO.add(convertOrderDetailDTO(orders));
            }
        }

        orderDTO.setStatus(order.getStatus());
        orderDTO.setDateOfOrder(order.getDateOfOrder());
        orderDTO.setOrderDetail(orderDetailDTO);

        return orderDTO;
    }

    public OrderDetailDTO convertOrderDetailDTO(OrderDetail orderDetail){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());

        return orderDetailDTO;
    }
}
