package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.mapper.OrderMapper;
import com.ideas2it.fooddeliverymanagement.model.Order;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.Role;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.UserRepository;
import com.ideas2it.fooddeliverymanagement.service.RoleService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * It performs create, read, update, delete (CRUD) operation for the user
 * Throw's custom exception if the user is not present in the database
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final RoleService roleService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public UserDTO addUser(UserDTO userDTO) throws FoodDeliveryManagementException {
        List<Role> roles = new ArrayList<>();
        UserDTO savedUser;
        User user = UserMapper.convertUser(userDTO);
        if (isEmailExist(user.getEmail())) {
            throw new FoodDeliveryManagementException(Constants.EMAIL_ALREADY_EXIST,HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (userDTO.getRoles().isEmpty()) {
                for (RoleDTO roleDTO: roleService.getAllRoles()){
                    if (roleDTO.getName().equals("CUSTOMER")){
                        roles.add(UserMapper.convertToRole(roleDTO));
                    }
                }
            } else {
                for (RoleDTO role : userDTO.getRoles()) {
                    roles.add(UserMapper.convertToRole(roleService.getRole(role.getId())));
                }
            }
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            savedUser = UserMapper.convertUserDTO(userRepository.save(user));

            if (savedUser != null) {
                return savedUser;
            }

        }
        throw new FoodDeliveryManagementException(Constants.USER_NOT_ADDED, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public UserDTO getUser(int userId)  throws FoodDeliveryManagementException {
       Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return (UserMapper.convertUserDTO(user.get()));
        }
        throw new FoodDeliveryManagementException(Constants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public UserDTO deleteUser(int userId) throws FoodDeliveryManagementException  {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            for (Address address : user.get().getAddresses()) {
                address.setDelete(true);
            }
            user.get().getRoles().clear();
            user.get().setDelete(true);
            return UserMapper.convertUserDTO(userRepository.save(user.get()));
        }
        throw new FoodDeliveryManagementException(Constants.USER_NOT_FOUND,HttpStatus.NOT_FOUND);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<UserDTO> getAllUsers() throws FoodDeliveryManagementException {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();

        if (!users.isEmpty()) {
            users.forEach(user -> userDTOS.add(UserMapper.convertUserDTO(user)));
            return userDTOS;
        }
        throw new FoodDeliveryManagementException(Constants.USER_NOT_FOUND,HttpStatus.NOT_FOUND);
    }

    /**
     *{@inheritDoc}
     */
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
        throw new FoodDeliveryManagementException(Constants.DETAILS_NOT_UPDATED,HttpStatus.UNPROCESSABLE_ENTITY);

    }
    /**
     *{@inheritDoc}
     */
    @Override
    public boolean isExist(int userId) {
        return userRepository.existsById(userId);
    }

    /**
     * Check weather the email is already exist or not
     *
     * @param email
     * @return true if the user is existed
     */
    private boolean isEmailExist(String email){
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Collection<SimpleGrantedAuthority> authorities = null;
        User user = (userRepository.findByName(name));

        if (user == null) {
            throw  new UsernameNotFoundException(Constants.USER_NOT_FOUND + name);
        }

        for (Role role : user.getRoles()) {
           authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }

    public List<OrderDTO> getOrderDetails(int userId) throws FoodDeliveryManagementException {
        User existingUser = userRepository.findById(userId).get();
        List<OrderDTO> existingOrders = null;
        if (!existingUser.getOrders().isEmpty()) {
            for (Order order : existingUser.getOrders()) {
                existingOrders.add(OrderMapper.convertOrderDTO(order));
            }
            return existingOrders;
        }
        throw new FoodDeliveryManagementException(Constants.NO_ORDER_DETAILS_FOUND,HttpStatus.NOT_FOUND);
    }
}
