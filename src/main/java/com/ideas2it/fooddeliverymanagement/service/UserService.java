package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> addUser(UserDTO userDTO);

}
