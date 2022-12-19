package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * CategoryRepository interface has the methods to handle category's operations
 * </p>
 *
 * @author Naganandhini
 *
 * @version 1.0 14-DEC-2022
 *
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    /**
     * <p>
     * To generate the category code as per the count of the categories
     * </p>
     *
     * @return a category code with prefix as CAT
     */
    @Query(value = "select count(id) from category" , nativeQuery = true)
    Long getCategoriesCount();

    /**
     * <p>
     * To soft delete the category for the given category id.
     * </p>
     *
     */
    @Modifying
    @Query("update Category set isDeleted = true where id = :id")
    void deleteById(@Param("id")int id);
}
