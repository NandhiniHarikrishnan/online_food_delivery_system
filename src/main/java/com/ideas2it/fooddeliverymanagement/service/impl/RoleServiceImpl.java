package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Role;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.RoleRepository;
import com.ideas2it.fooddeliverymanagement.service.RoleService;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * It performs create, read, delete for the role
 * Throws custom exception if the role is not present in database.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-16-10
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserMapper userMapper) {
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public RoleDTO addRole(RoleDTO newRole) throws FoodDeliveryManagementException {
        Role role = UserMapper.convertToRole(newRole);
        List<User> users;

        if (!newRole.getUserDTOS().isEmpty()) {
            users = new ArrayList<>();
            for (UserDTO userDTO : newRole.getUserDTOS()) {
                users.add(userMapper.convertToUser(userDTO));
            }
            role.setUsers(users);
        }
        role.setCode(generateCode());
        Optional<RoleDTO> savedRole = Optional.ofNullable(UserMapper.convertToRoleDTO(roleRepository.save(role)));

        if (savedRole.isPresent()) {
            return savedRole.get();
        }
        throw new FoodDeliveryManagementException(Constants.ROLE_NOT_ADDED,HttpStatus.UNPROCESSABLE_ENTITY);

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public RoleDTO getRole(int roleId) throws FoodDeliveryManagementException {
        Optional<Role> existingRole = roleRepository.findById(roleId);

        if (existingRole.isPresent()) {
            return UserMapper.convertToRoleDTO(existingRole.get());
        }
        throw new FoodDeliveryManagementException(Constants.ROLE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public RoleDTO deleteRole(int roleId) throws FoodDeliveryManagementException {
        Optional<Role> existingRole = roleRepository.findById(roleId);

        if (existingRole.isPresent()) {
            existingRole.get().setDelete(true);
            return UserMapper.convertToRoleDTO(roleRepository.save(existingRole.get()));
        }
        throw new FoodDeliveryManagementException(Constants.ROLE_NOT_DELETED,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<RoleDTO> getAllRoles() throws FoodDeliveryManagementException {
        List<RoleDTO> existingRoles = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();

        if (!roles.isEmpty()) {
            roles.forEach(role -> existingRoles.add(UserMapper.convertToRoleDTO(role)));
            return existingRoles;
        }
        throw new FoodDeliveryManagementException(Constants.ROLE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    /**
     * It generates a unique code for a role
     *
     * @return A String
     */
    private String generateCode() {
        long roleCount = roleRepository.count();
        return "ROLE-0"+ (++roleCount);
    }
}
