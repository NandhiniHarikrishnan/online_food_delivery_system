package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.AddressService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) throws FoodDeliveryManagementException {
        userService.addUser(userDTO);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") int userId)  throws FoodDeliveryManagementException {
        userService.getUser(userId);
        return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) throws FoodDeliveryManagementException  {
        userService.deleteUser(userId);
        return new ResponseEntity<>("ID " +userId+ " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser() throws FoodDeliveryManagementException {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) throws FoodDeliveryManagementException  {
        userService.updateUser(userDTO);
        return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
    }

    @PostMapping("/addAddress/{userId}")
    public ResponseEntity<String> addAddress(@PathVariable int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException  {
        addressService.addAddress(addressDTO, userId);
        return new ResponseEntity<>("Address Added Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{userId}/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable int userId, @PathVariable int addressId) throws FoodDeliveryManagementException  {
        addressService.deleteAddress(userId, addressId);
        return new ResponseEntity<>("Address deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{userId}")
    public ResponseEntity<String> updatedAddress(@PathVariable int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException {
        addressService.updateAddress(userId,addressDTO);
        return new ResponseEntity<>("Address updated successfully",HttpStatus.OK);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable int addressId) throws FoodDeliveryManagementException {
        return new ResponseEntity<>(addressService.getAddress(addressId),HttpStatus.OK);
    }

    @GetMapping("/getAllAddresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses() throws FoodDeliveryManagementException {
        return new ResponseEntity<>(addressService.getAllAddress(),HttpStatus.OK);
    }
}
