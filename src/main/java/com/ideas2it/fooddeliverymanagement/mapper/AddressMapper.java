package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.model.Address;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Handles the converting process from entity to DTO for AddressMapper.
 * </p
 *
 * @author Jeevanantham
 * @version 1.0 16-DEC-2022
 */
@Component
public class AddressMapper {
    public static AddressDTO convertAddressDTO(Address address) {
        AddressDTO addressDTO = null;
        Restaurant restaurant;
        if (null != address) {
            addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setPlotNumber(address.getPlotNumber());
            addressDTO.setStreet(address.getStreet());
            addressDTO.setArea(address.getArea());
            addressDTO.setCity(address.getCity());
            addressDTO.setDistrict(address.getDistrict());
            addressDTO.setState(address.getState());
            addressDTO.setPinCode(address.getPinCode());
            addressDTO.setPhoneNumber(address.getPhoneNumber());

            restaurant = address.getRestaurant();
            if (null != restaurant) {
                restaurant.setAddresses(null);
                addressDTO.setRestaurantDTO(RestaurantMapper.convertRestaurantDTO(restaurant));
            }
        }
        return addressDTO;
    }

    public static Address convertAddress(AddressDTO addressDTO) {
        Address address = null;
        RestaurantDTO restaurantDTO;
        if (null != addressDTO) {
            address = new Address();
            address.setId(addressDTO.getId());
            address.setPlotNumber(addressDTO.getPlotNumber());
            address.setStreet(addressDTO.getStreet());
            address.setCity(addressDTO.getCity());
            address.setDistrict(addressDTO.getDistrict());
            address.setState(addressDTO.getState());
            address.setPinCode(addressDTO.getPinCode());
            address.setPhoneNumber(addressDTO.getPhoneNumber());

            restaurantDTO = addressDTO.getRestaurantDTO();
            if (null != restaurantDTO) {
                restaurantDTO.setAddresses(null);
                address.setRestaurant(RestaurantMapper.convertRestaurant(restaurantDTO));
            }
        }
        return address;
    }
}
