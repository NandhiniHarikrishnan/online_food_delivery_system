/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.repository;

import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * A repository for the Restaurant class.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-15
 */
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

    /**
     * <p>
     * To get the restaurant by restaurant name.
     * </p>
     *
     * @param name - the restaurant name for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurant based on the given name
     */
    List<Restaurant> findByName(String name);

    /**
     * <p>
     * To search the restaurant based on it's location.
     * </p>
     *
     * @param location - the location for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurant based on the given location
     */
    @Query("select restaurant from Restaurant restaurant join fetch restaurant.addresses address where address.city = :location")
    List<Restaurant> searchRestaurantByLocation(@Param("location") String location);

    /**
     * <p>
     * To search the restaurant based on it's cuisine.
     * </p>
     *
     * @param cuisine - the cuisine for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurant based on the given cuisine
     */
    @Query("select restaurant from Restaurant restaurant join fetch restaurant.cuisine cuisine where cuisine.name = :cuisine")
    List<Restaurant> searchRestaurantByCuisine(@Param("cuisine") String cuisine);
}
