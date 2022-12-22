/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Used to get the order database.
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
