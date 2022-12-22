/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Creating a repository for the User class.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    /**
     * Find a user by their user name.
     *
     * @param userName The user name of the user to find.
     * @return A User object
     */
    User findByUserName(String userName);

    /**
     * Find a user by email.
     *
     * @param email The email address of the user you want to find.
     * @return A User object
     */
    User findByEmail(String email);
}
