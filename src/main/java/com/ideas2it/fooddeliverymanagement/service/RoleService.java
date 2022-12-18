package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

public interface RoleService {

    RoleDTO addRole(RoleDTO newRole)throws FoodDeliveryManagementException;

    RoleDTO getRole(int roleId) throws FoodDeliveryManagementException;

    RoleDTO deleteRole(int roleId) throws FoodDeliveryManagementException;
}
