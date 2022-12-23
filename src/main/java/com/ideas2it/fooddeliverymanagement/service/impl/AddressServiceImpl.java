/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.AddressRepository;
import com.ideas2it.fooddeliverymanagement.service.AddressService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * It performs basic CRUD operation to the address
 * It throws custom exception if the data is not present in the database
 * It stores only the persistent object in database, and it returns persistent object,
 * so it use mapper class to convert the object dto to persistent and vice versa.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public AddressDTO addAddress(AddressDTO addressDTO, int userId) throws FoodDeliveryManagementException {
        Address address = UserMapper.convertAddress(addressDTO);
        address.setUser(UserMapper.convertUser(userService.getUser(userId)));
        AddressDTO addedAddress = UserMapper.convertAddressDTO(addressRepository.save(address));

        if (addedAddress != null) {
            return addedAddress;
        }
        throw new FoodDeliveryManagementException(Constants.ADDRESS_NOT_ADDED, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public AddressDTO getAddress(int id) throws FoodDeliveryManagementException{
        Optional<Address> existingAddress = addressRepository.findById(id);

        if (existingAddress.isPresent()) {
            return UserMapper.convertAddressDTO(existingAddress.get());
        }
        throw new FoodDeliveryManagementException(Constants.ADDRESS_NOT_FOUND,HttpStatus.NOT_FOUND);

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public AddressDTO updateAddress(int userId, AddressDTO addressDTO) throws FoodDeliveryManagementException {
        Address address = UserMapper.convertAddress(addressDTO);
        address.setUser(UserMapper.convertToUser(userService.getUser(userId)));
        AddressDTO updatedAddress = UserMapper.convertAddressDTO(addressRepository.save(address));

        if (updatedAddress != null) {
            return updatedAddress;
        }
        throw new FoodDeliveryManagementException(Constants.ADDRESS_NOT_UPDATED,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String deleteAddress(int userID, int addressId) throws FoodDeliveryManagementException {
        if(userService.isExist(userID)) {
            User user = UserMapper.convertUser(userService.getUser(userID));

            for (Address address : user.getAddresses()) {
                if (address.getId() == addressId) {
                    address.setDelete(true);
                    address.setUser(user);
                    UserMapper.convertAddressDTO(addressRepository.save(address));
                    return Constants.DELETED_SUCCESSFULLY;
                }
            }
        }
       throw new FoodDeliveryManagementException(Constants.ADDRESS_NOT_DELETED,HttpStatus.NOT_FOUND);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<AddressDTO> getAllAddress() throws FoodDeliveryManagementException {
        List<AddressDTO> listOfAddresses = new ArrayList<>();
        List<Address> addresses = addressRepository.findAll();
        
        if (!addresses.isEmpty()) {
            addresses.forEach(address -> listOfAddresses.add(UserMapper.convertAddressDTO(address)));
            return listOfAddresses;
        }
        throw new FoodDeliveryManagementException(Constants.ADDRESS_NOT_FOUND,HttpStatus.NOT_FOUND);
    }

}
