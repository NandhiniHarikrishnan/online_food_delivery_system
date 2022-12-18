package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.Role;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.UserRepository;
import com.ideas2it.fooddeliverymanagement.service.RoleService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleService roleService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) throws FoodDeliveryManagementException{
        User user = UserMapper.convertUser(userDTO);
        List<RoleDTO> roles = userDTO.getRoles();
        List<Role> roles1 = new ArrayList<>();

        if (roles.isEmpty()){
            roles.add((roleService.getRole(1)));
        }
        for (RoleDTO roleDTO : roles) {
            roles1.add(UserMapper.convertToRole(roleDTO));
        }
        user.setRoles(roles1);

       UserDTO savedUser = UserMapper.convertUserDTO(userRepository.save(user));

        if (savedUser != null) {
            return savedUser;
        }
        throw new FoodDeliveryManagementException("USER_NOT_ADDED", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public UserDTO getUser(int userId)  throws FoodDeliveryManagementException {
       Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return (UserMapper.convertUserDTO(user.get()));
        }
        throw new FoodDeliveryManagementException("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @Override
    public UserDTO deleteUser(int userId) throws FoodDeliveryManagementException  {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            user.get().setDelete(true);
            return UserMapper.convertUserDTO(userRepository.save(user.get()));
        }
        throw new FoodDeliveryManagementException("USER_NOT_FOUND",HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserDTO> getAllUsers() throws FoodDeliveryManagementException {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            users.forEach(user -> userDTOS.add(UserMapper.convertUserDTO(user)));
            return userDTOS;
        }
        throw new FoodDeliveryManagementException("USER'S_NOT_FOUND",HttpStatus.NOT_FOUND);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws FoodDeliveryManagementException {
        List<Address> addresses = new ArrayList<>();
        User user = UserMapper.convertToUser(userDTO);

        for (AddressDTO addressDTO : userDTO.getAddresses()) {
            Address address = UserMapper.convertAddress(addressDTO);
            address.setUser(user);
            addresses.add(address);
        }
        user.setAddresses(addresses);
        UserDTO updatedUser = UserMapper.convertUserDTO(userRepository.save(user));

       if (updatedUser != null) {
           return updatedUser;
       }
        throw new FoodDeliveryManagementException("DETAILS_NOT_UPDATED",HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @Override
    public boolean isExist(int userId) {
        return userRepository.existsById(userId);
    }

}
