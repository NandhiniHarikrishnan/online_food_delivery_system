/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.AddressDTO;
import com.ideas2it.fooddeliverymanagement.dto.OrderDTO;
import com.ideas2it.fooddeliverymanagement.dto.UserDTO;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.model.AuthenticationRequest;
import com.ideas2it.fooddeliverymanagement.dto.AuthenticationResponse;
import com.ideas2it.fooddeliverymanagement.service.AddressService;
import com.ideas2it.fooddeliverymanagement.service.UserService;
import com.ideas2it.fooddeliverymanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * It exposes endpoints for the user to perform create, read, update and delete (CURD)
 * also get-users
 * Any one can add there details and easily register.
 * Except creating new user no one can directly access any url without valid credential
 * It exposes endpoints for the address to perform create, read, update and delete (CURD)
 * also get-addresses
 * Also generate the jwt token if the user-name and password matches.
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

    private final JwtUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, AddressService addressService,
                          JwtUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.addressService = addressService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }
    /**
     * It takes a new user object and valid there fields and then add in database.
     * If the user is added successfully it will return the saved user in the form of JSON
     *
     * @param userDTO This is the object that will be sent in the request body.
     * @return saved user
     * @throws FoodDeliveryManagementException
     */
    @PostMapping("/")
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO) throws FoodDeliveryManagementException {
        return userService.addUser(userDTO);
    }

    /**
     * Fetch the user information based on the user ID which is passed in the url.
     *
     * @param id The path variable that will be used to get the userId from the URL.
     * @return the user object in the form of JSON
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id)  throws FoodDeliveryManagementException {
        return userService.getUser(id);
    }

    /**
     * It deletes the user from the database by getting userId.
     * If user is deleted it return successful String message.
     *
     * @param id The id of the user to be deleted.
     * @return String message
     * @throws FoodDeliveryManagementException
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) throws FoodDeliveryManagementException  {
        return userService.deleteUser(id);
    }

    /**
     * It gets user's record in database
     *
     * @return user's in the form of JSON
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/")
    public List<UserDTO> getAllUser() throws FoodDeliveryManagementException {
        return userService.getAllUsers();
    }

    /**
     * Before updating the user it will validate the user fields once the validation complete
     * it will update the user details.
     *
     * @param userDTO The user object that needs to be updated.
     * @return updated user in the form of JSON.
     * @throws FoodDeliveryManagementException
     */
    @PutMapping("/{id}")
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable int id) throws FoodDeliveryManagementException  {
        return userService.updateUser(userDTO, id);
    }

    /**
     * It will validate the address fields and add the address to the particular
     * user by getting user ID
     *
     * @param userId The userId of the user to whom the address is to be added.
     * @param addressDTO This is the object that is sent by the user.
     * @return the saved AddressDTO in the form of JSON
     * @throws FoodDeliveryManagementException
     */
    @PostMapping("/address/{user-id}")
    public AddressDTO addAddress(@Valid @PathVariable("user-id") int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException  {
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
    @DeleteMapping("/address/{user-id}/{address-id}")
    public String deleteAddress(@PathVariable("user-id") int userId, @PathVariable("address-id") int addressId) throws FoodDeliveryManagementException  {
        return addressService.deleteAddress(userId, addressId);
    }

    /**
     * It will validate the fields of the address and then update the address
     *
     * @param userId The userId of the user whose address is to be updated.
     * @param addressDTO This is the object that contains the address details.
     * @return AddressDTO
     * @throws FoodDeliveryManagementException
     */
    @PutMapping("/address/{user-id}")
    public AddressDTO updatedAddress(@Valid @PathVariable("user-id") int userId, @RequestBody AddressDTO addressDTO) throws FoodDeliveryManagementException {
        return addressService.updateAddress(userId,addressDTO);
    }

    /**
     * Fetch the address with the help of address ID which is passed in the url
     *
     * @param addressId The id of the address to be fetched.
     * @return the addressDTO object in the form of JSON
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/address/{address-id}")
    public AddressDTO getAddress(@PathVariable("address-id") int addressId) throws FoodDeliveryManagementException {
        return addressService.getAddress(addressId);
    }

    /**
     * It fetches the addresses in the database.
     *
     * @return addresses in the form of JSON.
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/get-addresses")
    public List<AddressDTO> getAllAddresses() throws FoodDeliveryManagementException {
        return addressService.getAllAddress();
    }

    /**
     * It takes in a username and password, authenticates the user, generates a JWT token and returns it
     * If the username and password are not matches it will throw the exception.
     *
     * @param authenticationRequest This is the request object that contains the username and password.
     * @return A JSON Web token
     * @throws FoodDeliveryManagementException if username and password mismatch.
     */
    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws FoodDeliveryManagementException {
        final UserDetails userDetails;
        final String jsonToken;

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            throw new FoodDeliveryManagementException(Constants.INVALID_USERNAME_OR_PASSWORD, HttpStatus.BAD_REQUEST);
        }

        userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());

        jsonToken = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jsonToken);
    }

    /**
     * This function is used to get the order details of a particular user.
     *
     * @param userId The userId of the user whose order details are to be fetched.
     * @return List of OrderDTO
     * @throws FoodDeliveryManagementException
     */
    @GetMapping("/get-order-details/{user-id}")
    public List<OrderDTO> getOrderDetails(@PathVariable("user-id") int userId) throws FoodDeliveryManagementException {
        return userService.getOrderDetails(userId);
    }
}
