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

    User findByEmail(String email);
}
