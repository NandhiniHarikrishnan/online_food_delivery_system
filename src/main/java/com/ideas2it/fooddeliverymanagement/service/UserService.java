package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import javax.xml.stream.FactoryConfigurationError;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO addUser(UserDTO userDTO) throws FoodDeliveryManagementException;

   UserDTO getUser(int userId) throws FoodDeliveryManagementException ;

   boolean isExist(int userId) throws FoodDeliveryManagementException ;

   UserDTO deleteUser(int userId) throws FoodDeliveryManagementException ;

   List<UserDTO> getAllUsers() throws FoodDeliveryManagementException ;

   UserDTO updateUser(UserDTO userDTO) throws FoodDeliveryManagementException ;

}
