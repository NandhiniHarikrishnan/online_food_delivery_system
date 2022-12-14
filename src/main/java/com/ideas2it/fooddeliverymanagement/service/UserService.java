package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> addUser(UserDTO userDTO);

   Optional<UserDTO> getUser(int userID);

   boolean isExist(int userId);

   void deleteUser(int userId);

   List<UserDTO> getAllUsers();

   void updateUser(UserDTO userDTO);

}
