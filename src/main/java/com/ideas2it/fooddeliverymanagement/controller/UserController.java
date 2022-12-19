package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.AddressService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * It exposes endpoints for the user to perform CRUD operations on the user and address entities
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AddressService addressService;

    @Autowired
    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }
    /**
     * It takes a User object as a parameter to add the user, and returns the saved UserDTO object
     *
     * @param userDTO This is the object that will be sent in the request body.
     * @return UserDTO
     * @throws FoodDeliveryManagementException
     */
    @PostMapping("/")
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO) throws FoodDeliveryManagementException {
        return userService.addUser(userDTO);
    }

    /**
     * It returns a User object, which is in database that contains the user's information
     *
     * @param userId The path variable that will be used to get the userId from the URL.
     * @return UserDTO
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") int userId)  throws FoodDeliveryManagementException {
        return userService.getUser(userId);
    }

    /**
     * It deletes a user from the database by getting userId.
     *
     * @param userId The id of the user to be deleted.
     * @return UserDTO
     * @throws FoodDeliveryManagementException
     */
    @DeleteMapping("/{userId}")
    public UserDTO deleteUser(@PathVariable("userId") int userId) throws FoodDeliveryManagementException  {
        return userService.deleteUser(userId);
    }

    /**
     * It returns the list of user in database.
     *
     * @return user's
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/")
    public List<UserDTO> getAllUser() throws FoodDeliveryManagementException {
        return userService.getAllUsers();
    }

    /**
     * This function is used to update the user details
     *
     * @param userDTO The user object that needs to be updated.
     * @return UserDTO
     * @throws FoodDeliveryManagementException
     */
    @PutMapping("/")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) throws FoodDeliveryManagementException  {
        return userService.updateUser(userDTO);
    }

    /**
     * It takes in a userId and an addressDTO and returns a saved addressDTO
     *
     * @param userId The userId of the user to whom the address is to be added.
     * @param addressDTO This is the object that is sent by the user.
     * @return the saved AddressDTO
     * @throws FoodDeliveryManagementException
     */
    @PostMapping("/addAddress/{userId}")
    public AddressDTO addAddress(@PathVariable int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException  {
        return addressService.addAddress(addressDTO, userId);
    }

    /**
     * It deletes an address of a user by getting userId and addressId
     *
     * @param userId The userId of the user whose address is to be deleted.
     * @param addressId The id of the address to be deleted.
     * @return the deleted AddressDTO
     * @throws FoodDeliveryManagementException
     */
    @DeleteMapping("/deleteAddress/{userId}/{addressId}")
    public AddressDTO deleteAddress(@PathVariable int userId, @PathVariable int addressId) throws FoodDeliveryManagementException  {
        return addressService.deleteAddress(userId, addressId);
    }

    /**
     * It updates the address of the user.
     *
     * @param userId The userId of the user whose address is to be updated.
     * @param addressDTO This is the object that contains the address details.
     * @return AddressDTO
     * @throws FoodDeliveryManagementException
     */
    @PutMapping("/updateAddress/{userId}")
    public AddressDTO updatedAddress(@PathVariable int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException {
        return addressService.updateAddress(userId,addressDTO);
    }

    /**
     * Get the address by addressId with the given addressId
     *
     * @param addressId The id of the address to be fetched.
     * @return the existing AddressDTO
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/address/{addressId}")
    public AddressDTO getAddress(@PathVariable int addressId) throws FoodDeliveryManagementException {
        return addressService.getAddress(addressId);
    }

    /**
     * It returns a list of all the addresses in the database
     *
     * @return List of AddressDTO
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/getAllAddresses")
    public List<AddressDTO> getAllAddresses() throws FoodDeliveryManagementException {
        return addressService.getAllAddress();
    }
}
