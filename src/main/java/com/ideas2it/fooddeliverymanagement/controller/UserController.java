package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.service.UserService;
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
        Optional<UserDTO> user = userService.getUser(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {

        if (userService.isExist(userId)) {
            userService.deleteUser(userId);
            return new ResponseEntity<>("ID" +userId + " deleted successfully",HttpStatus.OK);
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

        if (userService.isExist(userDTO.getId())) {
            userService.updateUser(userDTO);
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
