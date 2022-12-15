package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.mapper.UserMapper;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.User;
import com.ideas2it.fooddeliverymanagement.repository.AddressRepository;
import com.ideas2it.fooddeliverymanagement.service.AddressService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<AddressDTO> addAddress(AddressDTO addressDTO, int userId) {
        Address address = userMapper.convertAddress(addressDTO);
        address.setUser(userMapper.convertUser(userService.getUser(userId)));
        return Optional.of(userMapper.convertAddressDTO(addressRepository.save(address)));

    }

//    public AddressDTO updateAddress(int addressId,AddressDTO addressDTO) {
//        Address addressOld = addressRepository.findById(addressId).get();
//        addressOld.setPlotNumber(addressDTO.getPlotNumber());
//        return userMapper.convertAddressDTO(addressRepository.save(addressOld));
//    }
    @Override
    public Optional<AddressDTO> getAddress(int id) {
        Address address = addressRepository.findById(id).get();
        return Optional.ofNullable(userMapper.convertAddressDTO(address));
    }

    @Override
    public void update(AddressDTO addressDTO) {
        addressRepository.save(userMapper.convertAddress(addressDTO));
    }

    @Override
    public Optional<AddressDTO> deleteAddress(int userID, int addressId) {
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
        return Optional.empty();
    }

}
