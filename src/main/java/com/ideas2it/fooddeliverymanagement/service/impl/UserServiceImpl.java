package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.UserRepository;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();

    @Override
    public Optional<UserDTO> addUser(UserDTO userDTO) throws FoodDeliveryManagementException{

        Optional<UserDTO> savedUser = Optional.ofNullable(userMapper.convertUserDTO(userRepository.save(userMapper.convertUser(userDTO))));

        if (savedUser.isPresent()) {
            return savedUser;
        }
        throw new FoodDeliveryManagementException("USER_NOT_ADDED", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public UserDTO getUser(int userId)  throws FoodDeliveryManagementException {
       Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return (userMapper.convertUserDTO(user.get()));
        }
        throw new FoodDeliveryManagementException("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<UserDTO> deleteUser(int userId) throws FoodDeliveryManagementException  {

        if(userRepository.existsById(userId)) {
                Optional<User> user = userRepository.findById(userId);
                user.get().setDelete(true);
                return Optional.ofNullable(userMapper.convertUserDTO(userRepository.save(user.get())));
        }
        throw new FoodDeliveryManagementException("USER_NOT_FOUND",HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserDTO> getAllUsers() throws FoodDeliveryManagementException {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            users.forEach(user -> userDTOS.add(userMapper.convertUserDTO(user)));
            return userDTOS;
        }
        throw new FoodDeliveryManagementException("USER'S_NOT_FOUND",HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<UserDTO> updateUser(UserDTO userDTO) throws FoodDeliveryManagementException {
        List<Address> addresses = new ArrayList<>();
        User user = userMapper.convertToUser(userDTO);

        for (AddressDTO addressDTO : userDTO.getAddresses()) {
            Address address = userMapper.convertAddress(addressDTO);
            address.setUser(user);
            addresses.add(address);
        }
        user.setAddresses(addresses);
       Optional<UserDTO> updatedUser = Optional.ofNullable((userMapper.convertUserDTO(userRepository.save(user))));

       if (updatedUser.isPresent()) {
           return updatedUser;
       }
        throw new FoodDeliveryManagementException("DETAILS_NOT_UPDATED",HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @Override
    public boolean isExist(int userId) throws FoodDeliveryManagementException {
        return userRepository.existsById(userId);
    }

}
