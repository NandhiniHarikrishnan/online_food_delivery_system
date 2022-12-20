package com.ideas2it.fooddeliverymanagement;

import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@SpringBootApplication
public class FoodDeliveryManagementApplication {

	public static void main(String[] args) {
			SpringApplication.run(FoodDeliveryManagementApplication.class, args);
	}

}
