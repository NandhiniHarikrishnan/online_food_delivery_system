package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * It exposes endpoints for adding, getting and deleting roles
 * The url path to enter is /role after it perform based on the url-path
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-16-10
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
     */
    @PostMapping("/")
    public RoleDTO addRole(@RequestBody RoleDTO roleDTO) throws FoodDeliveryManagementException {
        return roleService.addRole(roleDTO);
    }

    /**
     * Get the role with the given roleId
     *
     * @param roleId The id of the role to be fetched.
     * @return A RoleDTO object
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
     */
    @DeleteMapping("/{roleId}")
    public RoleDTO deleteRole(@PathVariable int roleId) throws FoodDeliveryManagementException {
        return roleService.deleteRole(roleId);
    }

    @GetMapping("/")
    public List<RoleDTO> getAllRole() throws FoodDeliveryManagementException {
        return roleService.getAllRoles();
    }
}
