package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    /**
     * <p>
     * To search the restaurant.
     * </p>
     *
     * @param keyword - an input for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurant based on the given keyword
     */
    @Query("select r from Restaurant r where r.name like %:keyword%")
    List<Restaurant> searchRestaurant(@Param("keyword") String keyword);

}
