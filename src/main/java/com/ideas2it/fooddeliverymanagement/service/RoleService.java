package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import java.util.List;

/**
 * It performs create, read, delete for the role
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-16-10
 */
public interface RoleService {

    /**
     * This function adds a new role to the database
     *
     * @param newRole The new role to be added.
     * @return RoleDTO
     * @throws FoodDeliveryManagementException
     */
    RoleDTO addRole(RoleDTO newRole)throws FoodDeliveryManagementException;

    /**
     * This function returns the role details of a particular role by getting roleId
     *
     * @param roleId The id of the role to be fetched.
     * @return RoleDTO
     * @throws FoodDeliveryManagementException
     */
    RoleDTO getRole(int roleId) throws FoodDeliveryManagementException;

    /**
     * It deletes a role from the database by getting roleId
     *
     * @param roleId The id of the role to be deleted.
     * @return RoleDTO
     * @throws FoodDeliveryManagementException
     */
    RoleDTO deleteRole(int roleId) throws FoodDeliveryManagementException;

    /**
     * This function returns a list of all the roles in the system
     *
     * @return List of RoleDTO objects
     * @throws FoodDeliveryManagementException
     */
    List<RoleDTO> getAllRoles() throws FoodDeliveryManagementException;
}
