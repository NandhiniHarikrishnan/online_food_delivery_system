package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<String> addRole(@RequestBody RoleDTO roleDTO) throws FoodDeliveryManagementException {
        roleService.addRole(roleDTO);
        return new ResponseEntity<>("ROLE_ADDED_SUCCESSFULLY", HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public RoleDTO getRole(@PathVariable int roleId) throws FoodDeliveryManagementException {
        return roleService.getRole(roleId);
    }

    @DeleteMapping("/{roleId}")
    public RoleDTO deleteRole(@PathVariable int roleId) throws FoodDeliveryManagementException {
        return roleService.deleteRole(roleId);
    }
}
