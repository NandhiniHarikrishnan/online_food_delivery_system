package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.RoleDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.Role;
import com.ideas2it.fooddeliverymanagement.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
/**
 * It converts a UserDTO to a User and vice versa
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
public class UserMapper {
    public static User convertUser(UserDTO userDTO) {
        User user = convertToUser(userDTO);
        List<Address> addresses = new ArrayList<>();
        List<Role> roles = new ArrayList<>();

        if (userDTO.getAddresses() != null) {
            for (AddressDTO userAddress : userDTO.getAddresses()) {
                userAddress.setRestaurantDTO(null);
                addresses.add(convertAddress(userAddress));
            }
        }

        if (userDTO.getRoles() != null) {
            for (RoleDTO roleDTO : userDTO.getRoles()) {
                roleDTO.getUserDTOS().clear();
                roles.add(convertToRole(roleDTO));
            }
        }
        user.setRoles(roles);
        user.setAddresses(addresses);
        return user;
    }

    public static UserDTO convertUserDTO(User user) {
        UserDTO userDTO = convertToUserDTO(user);
        List<AddressDTO> addresses = new ArrayList<>();
        List<RoleDTO> roles = new ArrayList<>();

        if (!user.getAddresses().isEmpty()) {
            for (Address address : user.getAddresses()) {
                address.setRestaurant(null);
                address.setUser(null);
                addresses.add(convertAddressDTO(address));
            }
        }

        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                role.getUsers().clear();
                roles.add(convertToRoleDTO(role));
            }
        }
        userDTO.setRoles(roles);
        userDTO.setAddresses(addresses);
        return userDTO;
    }

    public static AddressDTO convertAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        User user = address.getUser();
        UserDTO userDTO = null;

        if (user != null) {
            userDTO = convertToUserDTO(user);
        }
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setPlotNumber(address.getPlotNumber());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setArea(address.getArea());
        addressDTO.setPhoneNumber(address.getPhoneNumber());
        addressDTO.setState(address.getState());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setUser(userDTO);

        return addressDTO;
    }

    public static User convertToUser(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        return userDTO;
    }
    public static Address convertAddress(AddressDTO addressDTO) {
        Address address = new Address();

        if (addressDTO != null) {
            address.setId(addressDTO.getId());
            address.setCity(addressDTO.getCity());
            address.setArea(addressDTO.getArea());
            address.setPinCode(addressDTO.getPinCode());
            address.setDistrict(addressDTO.getDistrict());
            address.setPlotNumber(addressDTO.getPlotNumber());
            address.setStreet(addressDTO.getStreet());
            address.setPhoneNumber(addressDTO.getPhoneNumber());
            address.setState(addressDTO.getState());
            address.setPhoneNumber(addressDTO.getPhoneNumber());
        }
        return address;
    }

    public static Role convertToRole(RoleDTO roleDTO) {
        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setCode(roleDTO.getCode());
        return role;
    }

    public static RoleDTO convertToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        List<UserDTO> userDTOS = new ArrayList<>();

        if (role.getUsers() != null) {
            for (User user : role.getUsers()) {
                userDTOS.add(convertToUserDTO(user));
            }
        }
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setCode(role.getCode());
        roleDTO.setUserDTOS(userDTOS);

        return roleDTO;
    }
}

