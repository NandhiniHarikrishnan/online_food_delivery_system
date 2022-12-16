package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Food;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@Component
public class OrderDetailDTO {
        private int id;
        private float price;
        private int quantity;
        @ManyToOne(fetch = FetchType.LAZY)
        private Food food;
}
