package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> addUser(UserDTO userDTO);

   UserDTO getUser(int userID);

   boolean isExist(int userId);

   Optional<UserDTO> deleteUser(int userId);

   List<UserDTO> getAllUsers();

   Optional<UserDTO> updateUser(UserDTO userDTO);

}
