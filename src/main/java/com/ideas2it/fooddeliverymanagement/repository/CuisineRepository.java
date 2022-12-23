package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Cuisine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * CuisineRepository interface has the methods to handle cuisine's operations
 * </p>
 *
 * @author Jeevanantham
 *
 * @version 1.0
 * @since 14-DEC-2022
 *
 */
@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {

    /**
     * <p>
     * To generate the cuisine code as per the count of the cuisine
     * </p>
     *
     * @return a category code
     */
    @Query(value = "select count(id) from cuisine" , nativeQuery = true)
    Long getCuisineCount();

    /**
     * <p>
     * To soft delete the cuisine for the given cuisine id.
     * </p>
     *
     */
    @Modifying
    @Query("update Cuisine set isDeleted = true where id = :id")
    void deleteById(@Param("id")int id);

    /**
     * <p>
     * Find a Cuisine by its name.
     * <p>
     *
     * @param name The name of the method.
     * @return A Cuisine object
     */
    Cuisine findByName(String name);
}