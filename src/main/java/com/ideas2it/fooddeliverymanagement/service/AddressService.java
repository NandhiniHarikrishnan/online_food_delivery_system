package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;

import java.util.Optional;

public interface AddressService {

    Optional<AddressDTO> addAddress(AddressDTO addressDTO, int userId);

    Optional<AddressDTO> getAddress(int AddressId);

    void update(AddressDTO addressDTO);

    Optional<AddressDTO> deleteAddress(int userID, int addressId);
}
