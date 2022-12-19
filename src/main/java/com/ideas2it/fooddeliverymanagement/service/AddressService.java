package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

import java.util.List;

/**
 * It performs create, read, update, delete (CRUD) for the address
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
public interface AddressService {

    /**
     * It adds an address to the database.
     *
     * @param addressDTO This is the address object that you want to add.
     * @param userId The userId of the user who is adding the address.
     * @return AddressDTO
     * @throws FoodDeliveryManagementException
     */
    AddressDTO addAddress(AddressDTO addressDTO, int userId) throws FoodDeliveryManagementException;

    /**
     * This function is used to get the address of the customer
     *
     * @param AddressId The id of the address to be fetched.
     * @return AddressDTO
     * @throws FoodDeliveryManagementException
     */
    AddressDTO getAddress(int AddressId)throws FoodDeliveryManagementException;

    /**
     * It updates the address of the user with the given userId.
     *
     * @param userId The userId of the user whose address is to be updated.
     * @param addressDTO This is the address object that needs to be updated.
     * @return AddressDTO
     * @throws FoodDeliveryManagementException
     */
    AddressDTO updateAddress(int userId,AddressDTO addressDTO) throws FoodDeliveryManagementException;

    /**
     * It deletes an address from the database by getting addressId.
     *
     * @param userID The userID of the user whose address is to be deleted.
     * @param addressId The address id of the address to be deleted.
     * @return AddressDTO
     * @throws FoodDeliveryManagementException
     */
    AddressDTO deleteAddress(int userID, int addressId) throws FoodDeliveryManagementException;

    /**
     * It returns a list of all the addresses in the database
     *
     * @return List of AddressDTO
     * @throws FoodDeliveryManagementException
     */
    List<AddressDTO> getAllAddress() throws FoodDeliveryManagementException ;
}
