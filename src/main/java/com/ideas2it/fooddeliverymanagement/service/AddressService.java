package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    AddressDTO addAddress(AddressDTO addressDTO, int userId) throws FoodDeliveryManagementException;

    AddressDTO getAddress(int AddressId)throws FoodDeliveryManagementException;

    AddressDTO updateAddress(int userId,AddressDTO addressDTO) throws FoodDeliveryManagementException;

    AddressDTO deleteAddress(int userID, int addressId) throws FoodDeliveryManagementException;

    List<AddressDTO> getAllAddress() throws FoodDeliveryManagementException ;
}
