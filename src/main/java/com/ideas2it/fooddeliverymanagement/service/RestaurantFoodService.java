package com.ideas2it.fooddeliverymanagement.service;

/**
 * RestaurantFoodServiceImpl method is used to get extract price from restaurant
 * by passing restaurant id and food id to get extract price from restaurant.
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
public interface RestaurantFoodService {

    /**
     * getPrice method is used to get the price from restaurant.
     *
     * @param foodId to select food
     * @param restaurantId to select restaurant
     * @return price for food in specified restaurant
     */
    float getPrice(int foodId,int restaurantId);

}
