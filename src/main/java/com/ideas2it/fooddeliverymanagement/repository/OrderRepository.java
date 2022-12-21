package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderRepository is used to get
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
