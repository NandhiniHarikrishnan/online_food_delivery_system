package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query("update User set isDeleted = true where id = ?1")
    void deleteUserById(@Param("userId") int userId);

    @Query("SELECT user FROM User user WHERE user.isDeleted = false")
    List<User> findAllActiveUsers();

    @Query("select user from User user where user.isDeleted = false and user.id = ?1")
    User getUserById(@Param("userId") int userId);

/*    @Query("select user from User user where user.isDeleted = false and user.id = ?1")
    boolean existsById(@Param("userId") int userId);*/


}
