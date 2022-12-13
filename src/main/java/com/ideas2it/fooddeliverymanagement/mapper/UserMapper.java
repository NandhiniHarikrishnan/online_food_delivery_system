package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public User convertUser(UserDTO userDTO) {
        User user = new User();
        List<Address> addresses = new ArrayList<>();
//        List<Role> userRole = null;
//        List<RoleDTO> roles = userDTO.getRoles();
        List<AddressDTO> userAddresses = userDTO.getAddresses();


        if (userAddresses != null) {
            for (AddressDTO userAddress : userAddresses) {
                addresses.add(convertAddress(userAddress));
            }
        }

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddresses(addresses);

        return user;
    }

    public Address convertAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setCity(addressDTO.getCityName());
        address.setPinCode(addressDTO.getPinCode());
        address.setDistrict(addressDTO.getDistrictName());
        address.setPlotNumber(addressDTO.getPlotNumber());
        address.setStreetName(addressDTO.getStreetName());
        address.setState(addressDTO.getStateName());
        address.setPhoneNumber(addressDTO.getPhoneNumber());

        return address;
    }

    public UserDTO convertUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        List<AddressDTO> addresses = new ArrayList<>();
        List<Address> userAddresses = user.getAddresses();

        if (!userAddresses.isEmpty()) {
            for (Address address : userAddresses) {
                addresses.add(convertAddressDTO(address));
            }
        }

        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddresses(addresses);

        return userDTO;
    }

    public AddressDTO convertAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setCityName(address.getCity());
        addressDTO.setDistrictName(address.getDistrict());
        addressDTO.setPlotNumber(address.getPlotNumber());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setPhoneNumber(address.getPhoneNumber());
        addressDTO.setStateName(address.getState());
        addressDTO.setStreetName(address.getStreetName());

        return addressDTO;
    }
}

