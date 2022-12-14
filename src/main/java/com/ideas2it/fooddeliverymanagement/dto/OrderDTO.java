package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.BaseModel;
import com.ideas2it.fooddeliverymanagement.model.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO extends BaseModel {
    private int id;
    private String status;
    private LocalDate dateOfOrder;
    private List<OrderDetailDTO> orderDetail;
}
