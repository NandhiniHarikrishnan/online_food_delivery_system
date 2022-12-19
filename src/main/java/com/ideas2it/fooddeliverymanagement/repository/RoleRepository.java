package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Creating a repository for the Role class.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-16-10
 */

@Repository
<<<<<<< Updated upstream
public interface RoleRepository extends JpaRepository<Role, Integer> {
=======
public interface RoleRepository extends JpaRepository<Role, Integer>{

>>>>>>> Stashed changes

}
