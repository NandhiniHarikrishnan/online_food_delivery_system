package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating a repository for the User address.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
