package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.AddressRepository;
import com.ideas2it.fooddeliverymanagement.service.AddressService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public AddressDTO addAddress(AddressDTO addressDTO, int userId) throws FoodDeliveryManagementException {
        Address address = UserMapper.convertAddress(addressDTO);
        address.setUser(UserMapper.convertUser(userService.getUser(userId)));
        AddressDTO addedAddress = UserMapper.convertAddressDTO(addressRepository.save(address));

        if (addedAddress != null) {
            return addedAddress;
        }
        throw new FoodDeliveryManagementException("ADDRESS_NOT ADDED", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @Override
    public AddressDTO getAddress(int id) throws FoodDeliveryManagementException{
        Optional<Address> existingAddress = addressRepository.findById(id);

        if (existingAddress.isPresent()) {
            return UserMapper.convertAddressDTO(existingAddress.get());
        }
        throw new FoodDeliveryManagementException("ADDRESS_NOT_FOUND",HttpStatus.NOT_FOUND);

    }

    @Override
    public AddressDTO updateAddress(int userId, AddressDTO addressDTO) throws FoodDeliveryManagementException {
        Address address = UserMapper.convertAddress(addressDTO);
        address.setUser(UserMapper.convertToUser(userService.getUser(userId)));
        AddressDTO updatedAddress = UserMapper.convertAddressDTO(addressRepository.save(address));

        if (updatedAddress != null) {
            return updatedAddress;
        }
        throw new FoodDeliveryManagementException("ADDRESS_NOT_UPDATED",HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public AddressDTO deleteAddress(int userID, int addressId) throws FoodDeliveryManagementException {
        if(userService.isExist(userID)) {
            User user = UserMapper.convertUser(userService.getUser(userID));

            for (Address address : user.getAddresses()) {
                if (address.getId() == addressId) {
                    address.setDelete(true);
                    address.setUser(user);
                    return UserMapper.convertAddressDTO(addressRepository.save(address));
                }
            }
        }
       throw new FoodDeliveryManagementException("ADDRESS_NOT_DELETED",HttpStatus.NOT_FOUND);
    }

    @Override
    public List<AddressDTO> getAllAddress() throws FoodDeliveryManagementException {
        List<AddressDTO> listOfAddresses = new ArrayList<>();
        List<Address> addresses = addressRepository.findAll();
        
        if (!addresses.isEmpty()) {
            addresses.forEach(address -> listOfAddresses.add(UserMapper.convertAddressDTO(address)));
            return listOfAddresses;
        }
        throw new FoodDeliveryManagementException("NO_ADDRESS_FOUND",HttpStatus.NOT_FOUND);
    }

}
