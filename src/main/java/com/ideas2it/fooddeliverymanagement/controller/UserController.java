package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.AddressService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * It's handles all the requests related to the user
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AddressService addressService;

    @Autowired
    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }
    @PostMapping("/")
    public UserDTO addUser(@RequestBody UserDTO userDTO) throws FoodDeliveryManagementException {
        return userService.addUser(userDTO);
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") int userId)  throws FoodDeliveryManagementException {
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public UserDTO deleteUser(@PathVariable("userId") int userId) throws FoodDeliveryManagementException  {
        return userService.deleteUser(userId);
    }

    @GetMapping("/")
    public List<UserDTO> getAllUser() throws FoodDeliveryManagementException {
        return userService.getAllUsers();
    }

    @PutMapping("/")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) throws FoodDeliveryManagementException  {
        return userService.updateUser(userDTO);
    }

    @PostMapping("/addAddress/{userId}")
    public AddressDTO addAddress(@PathVariable int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException  {
        return addressService.addAddress(addressDTO, userId);
    }

    @DeleteMapping("/deleteAddress/{userId}/{addressId}")
    public AddressDTO deleteAddress(@PathVariable int userId, @PathVariable int addressId) throws FoodDeliveryManagementException  {
        return addressService.deleteAddress(userId, addressId);
    }

    @PutMapping("/updateAddress/{userId}")
    public AddressDTO updatedAddress(@PathVariable int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException {
        return addressService.updateAddress(userId,addressDTO);
    }

    @GetMapping("/address/{addressId}")
    public AddressDTO getAddress(@PathVariable int addressId) throws FoodDeliveryManagementException {
        return addressService.getAddress(addressId);
    }

    @GetMapping("/getAllAddresses")
    public List<AddressDTO> getAllAddresses() throws FoodDeliveryManagementException {
        return addressService.getAllAddress();
    }
}
