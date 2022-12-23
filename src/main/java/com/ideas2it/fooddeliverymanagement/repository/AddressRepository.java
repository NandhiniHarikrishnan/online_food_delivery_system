/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the User address.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
