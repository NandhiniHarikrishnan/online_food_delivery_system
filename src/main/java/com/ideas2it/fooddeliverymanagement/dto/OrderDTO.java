package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Component
public class OrderDTO {
    private int id;
    private String status;
    private LocalDate dateOfOrder;
    private List<OrderDetailDTO> orderDetail;
}
