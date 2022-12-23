/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * It exposes endpoints for add, get, getAllRoles and delete role
 * The admin have the one and only authority of creat, get, getAllRoles and delete
 * After the complete validation only it allow to create new role and also for update
 * The url path to enter is role after it perform based on the url-path
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-18
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    /**
     * This function is used to add a new role to the database.
     * It validates the fields of the role.
     *
     * @param roleDTO it is a new role which is trying to add in database
     * @return the saved role in database in the form of JSON
     * @throws FoodDeliveryManagementException
     */
    @PostMapping("/")
    public RoleDTO addRole(@Valid @RequestBody RoleDTO roleDTO) throws FoodDeliveryManagementException {
        return roleService.addRole(roleDTO);
    }

    /**
     * Fetch the role which is present in the database with the help of the ID.
     *
     * @param id The id of the role to be fetched.
     * @return the particular role if present
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/{id}")
    public RoleDTO getRole(@PathVariable int id) throws FoodDeliveryManagementException {
        return roleService.getRole(id);
    }

    /**
     * It deletes a role from the database with the given roleId in path variable.
     *
     * @param id The id of the role to be deleted.
     * @return the successful message if the role is deleted in the database else it throws custom exception.
     * @throws FoodDeliveryManagementException
     */
    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable int id) throws FoodDeliveryManagementException {
        return roleService.deleteRole(id);
    }

    /**
     * It gets all the records about roles present in the database.
     *
     * @return the roles as a JSON format
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/")
    public List<RoleDTO> getAllRole() throws FoodDeliveryManagementException {
        return roleService.getAllRoles();
    }
}
