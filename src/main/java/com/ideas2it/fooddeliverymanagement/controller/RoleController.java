package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @PostMapping("/")
    public ResponseEntity<String> addRole(@RequestBody RoleDTO roleDTO) {

        return new ResponseEntity<>("ROLE_ADDED_SUCCESSFULLY", HttpStatus.OK);
    }

}
