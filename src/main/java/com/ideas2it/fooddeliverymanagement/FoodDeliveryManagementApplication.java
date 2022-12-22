/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 *
 * An online food delivery system allows customer to view the menu
 * of the restaurant by searching it based on the location,food category
 * and cuisine, an Admin assign the delivery boy to the order based on
 * their availability.
 *
 */
package com.ideas2it.fooddeliverymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class of Food delivery management application
 * which manage the orders placed by the user through the restaurant.
 */
@SpringBootApplication
public class FoodDeliveryManagementApplication {

	public static void main(String[] args) {
			SpringApplication.run(FoodDeliveryManagementApplication.class, args);
	}

}
