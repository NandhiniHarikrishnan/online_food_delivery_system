package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDetailDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order convertOrder(OrderDTO orderDTO) {
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
        order.setRestaurant(RestaurantMapper.convertRestaurant(orderDTO.getRestaurant()));
        order.setOrderDetails(orderDetails);
        return order;
    }

    public static OrderDetail convertOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        RestaurantFood restaurantFood = new RestaurantFood();
        orderDetail.setPrice(orderDetailDTO.getPrice());
        getPrice(restaurantFood);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setFood(FoodMapper.convertIntoEntity(orderDetailDTO.getFood()));
        return orderDetail;
    }

    public static void getPrice(RestaurantFood restaurantFood) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPrice(restaurantFood.getPrice());
    }

    public static OrderDTO convertOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        List<OrderDetail> orderDetail = order.getOrderDetails();
        List<Restaurant> restaurant = (List<Restaurant>) order.getRestaurant();

        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
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
        RestaurantFoodDTO restaurantFoodDTO = new RestaurantFoodDTO();
        RestaurantFood restaurantFood = new RestaurantFood();
        Food food;
        orderDetailDTO.setId((orderDetail.getId()));
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());

        orderDetailDTO.setFood(FoodMapper.convertIntoDTO(orderDetail.getFood()));
        return orderDetailDTO;
    }
}