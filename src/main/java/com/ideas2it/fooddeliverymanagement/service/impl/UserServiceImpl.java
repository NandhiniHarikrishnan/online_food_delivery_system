package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.UserRepository;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = new UserMapper();

    @Override
    public Optional<UserDTO> addUser(UserDTO userDTO) {

        User user = userMapper.convertUser(userDTO);
        return Optional.ofNullable(userMapper.convertUserDTO(userRepository.save(user)));
    }

    @Override
    public Optional<UserDTO> getUser(int userID) {
        User user = userRepository.getUserById(userID);

        if(user != null) {
            return Optional.ofNullable((userMapper.convertUserDTO(user)));
        }
        return Optional.empty();


    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteUserById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAllActiveUsers();

        if (!users.isEmpty()) {
            users.forEach(user -> userDTOS.add(userMapper.convertUserDTO(user)));
            return userDTOS;
        }
        return userDTOS;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userRepository.save(userMapper.convertUser(userDTO));
    }

    @Override
    public boolean isExist(int userId) {
        return userRepository.existsById(userId);
    }

}
