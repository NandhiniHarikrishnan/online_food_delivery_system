package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
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
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        Optional<UserDTO> savedUser= userService.addUser(userDTO);

        if (savedUser.isPresent()) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("user not created",HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") int userId) {
        UserDTO user = userService.getUser(userId);
        if (user != null) {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {

        if (userService.isExist(userId)) {
            Optional<UserDTO> userDTO = userService.deleteUser(userId);
            if (userDTO.isPresent()) {
                return new ResponseEntity<>("ID" + userId + " deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> users = userService.getAllUsers();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        Optional<UserDTO> updatedUser = userService.updateUser(userDTO);

        if (updatedUser.isPresent()) {
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addAddress/{userId}")
    public ResponseEntity<String> addAddress(@PathVariable int userId, @RequestBody AddressDTO addressDTO) {
        Optional<AddressDTO> savedAddress = addressService.addAddress(addressDTO, userId);

        if (savedAddress.isPresent()) {
            return new ResponseEntity<>("Address Added Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Address Not Added",HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @DeleteMapping("/deleteAddress/{userId}/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable int userId, @PathVariable int addressId) {
        Optional<AddressDTO> deletedAddress= addressService.deleteAddress(userId, addressId);

        if (deletedAddress.isPresent()) {
            return new ResponseEntity<>("Address deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Address not found",HttpStatus.NOT_FOUND);

    }
}
