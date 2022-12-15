package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.UserRepository;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<UserDTO> addUser(UserDTO userDTO) {

        User user = userMapper.convertUser(userDTO);
        return Optional.ofNullable(userMapper.convertUserDTO(userRepository.save(user)));
    }

    @Override
    public UserDTO getUser(int userID) {
       Optional<User> user = userRepository.findById(userID);

        if(user.isPresent()) {
            return (userMapper.convertUserDTO(user.get()));
        }
        return null;
    }

    @Override
    public Optional<UserDTO> deleteUser(int userId) {

        if(userRepository.existsById(userId)) {
                Optional<User> user = userRepository.findById(userId);
                user.get().setDelete(true);
                return Optional.ofNullable(userMapper.convertUserDTO(userRepository.save(user.get())));
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            users.forEach(user -> userDTOS.add(userMapper.convertUserDTO(user)));
            return userDTOS;
        }
        return userDTOS;
    }

    @Override
    public Optional<UserDTO> updateUser(UserDTO userDTO)  {
        List<Address> addresses = new ArrayList<>();
        User user = userMapper.convertToUser(userDTO);

        for (AddressDTO addressDTO : userDTO.getAddresses()) {
            Address address = userMapper.convertAddress(addressDTO);
            address.setUser(user);
            addresses.add(address);
        }
        user.setAddresses(addresses);
       return Optional.ofNullable(userMapper.convertUserDTO(userRepository.save(user)));

    }

    @Override
    public boolean isExist(int userId) {
        return userRepository.existsById(userId);
    }

}
