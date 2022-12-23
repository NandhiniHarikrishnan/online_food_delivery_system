package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * FoodRepository interface has the methods to handle food's operations
 * <p>
 *
 * @author - jeevanantham K
 * @version - 1.0
 * @since 16-DEC-2022
 */
@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    /**
     * <p>
     * To search the food.
     * </p>
     *
     * @param keyword - an input for which food will be filtered
     * @return - the list of filtered foods
     */
    @Query("select f from Food f where f.name like %:keyword% or f.description like %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);

    /**
     * <p>
     * To search the food by Category details.
     * </p>
     *
     * @param name - an input for which food by category will be filtered
     * @return - the list of filtered category
     */
    @Query("select f from Food f join f.category c where c.name =:name")
    List<Food> searchFoodByCategory(@Param("name") String name);
}

