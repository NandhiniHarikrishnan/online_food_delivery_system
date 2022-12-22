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
 * It exposes endpoints for adding, getting and deleting roles
 * The url path to enter is /role after it perform based on the url-path
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-10-16
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
     * This function is used to add a new role to the database
     *
     * @param roleDTO This is the object that is passed to the method.
     * @return RoleDTO
     * @throws FoodDeliveryManagementException
     */
    @PostMapping("/")
    public RoleDTO addRole(@Valid @RequestBody RoleDTO roleDTO) throws FoodDeliveryManagementException {
        return roleService.addRole(roleDTO);
    }

    /**
     * Get the role with the given roleId
     *
     * @param roleId The id of the role to be fetched.
     * @return A RoleDTO object
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/{roleId}")
    public RoleDTO getRole(@PathVariable int roleId) throws FoodDeliveryManagementException {
        return roleService.getRole(roleId);
    }

    /**
     * It deletes a role from the database with the given roleId.
     *
     * @param roleId The id of the role to be deleted.
     * @return RoleDTO
     * @throws FoodDeliveryManagementException
     */
    @DeleteMapping("/{roleId}")
    public RoleDTO deleteRole(@PathVariable int roleId) throws FoodDeliveryManagementException {
        return roleService.deleteRole(roleId);
    }

    /**
     * It returns a list of all the roles in the database
     *
     * @return List of RoleDTO
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/")
    public List<RoleDTO> getAllRole() throws FoodDeliveryManagementException {
        return roleService.getAllRoles();
    }
}
