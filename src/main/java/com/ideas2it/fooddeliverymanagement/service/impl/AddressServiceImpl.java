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

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserService userService;
    private final UserMapper userMapper = new UserMapper();
    @Override
    public Optional<AddressDTO> addAddress(AddressDTO addressDTO, int userId) throws FoodDeliveryManagementException {
        Address address = userMapper.convertAddress(addressDTO);
        address.setUser(userMapper.convertUser(userService.getUser(userId)));
        Optional<AddressDTO> addedAddress = Optional.ofNullable((userMapper.convertAddressDTO(addressRepository.save(address))));

        if (addedAddress.isPresent()) {
            return addedAddress;
        }
        throw new FoodDeliveryManagementException("ADDRESS_NOT ADDED", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @Override
    public AddressDTO getAddress(int id) throws FoodDeliveryManagementException{

        if (addressRepository.existsById(id)) {
            return userMapper.convertAddressDTO(addressRepository.findById(id).get());
        } else {
            throw new FoodDeliveryManagementException("ADDRESS_NOT_FOUND",HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Optional<AddressDTO> updateAddress(int userId, AddressDTO addressDTO) throws FoodDeliveryManagementException {
        Address address = userMapper.convertAddress(addressDTO);
        address.setUser(userMapper.convertToUser(userService.getUser(userId)));
        Optional<AddressDTO> updatedAddress = Optional.ofNullable(userMapper.convertAddressDTO(addressRepository.save(address)));

        if (updatedAddress.isPresent()) {
            return updatedAddress;
        }
        throw new FoodDeliveryManagementException("ADDRESS_NOT_UPDATED",HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    public Optional<AddressDTO> deleteAddress(int userID, int addressId) throws FoodDeliveryManagementException {
        if(userService.isExist(userID)) {
            User user = userMapper.convertUser(userService.getUser(userID));

            for (Address address : user.getAddresses()) {
                if (address.getId() == addressId) {
                    address.setDelete(true);
                    address.setUser(user);
                    return Optional.ofNullable(userMapper.convertAddressDTO(addressRepository.save(address)));
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
            addresses.forEach(address -> listOfAddresses.add(userMapper.convertAddressDTO(address)));
            return listOfAddresses;
        }
        throw new FoodDeliveryManagementException("NO_ADDRESS_FOUND",HttpStatus.NOT_FOUND);
    }

}
