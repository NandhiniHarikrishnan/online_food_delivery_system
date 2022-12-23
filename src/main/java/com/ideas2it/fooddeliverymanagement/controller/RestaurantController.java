package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDetailDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * It exposes endpoints for the admin to perform CRUD and search operations on the restaurant.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-15
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * <p>
     * To Create the new restaurant if the given values are valid that is
     * the restaurant name with same address is already exist or not.
     * </p>
     *
     * @param restaurantDTO - the restaurant values to be created
     * @return - the created restaurant
     * @throws FoodDeliveryManagementException - if the restaurant name with same address
     *                                           is already exist in the category table.
     */
    @PostMapping("/")
    private RestaurantDTO addRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException{
        return restaurantService.addRestaurant(restaurantDTO);
    }

    /**
     * <p>
     * To display all the existing restaurants stored in the restaurant table.
     * </p>
     *
     * @return - the list of existing restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants in the restaurant table
     */
    @GetMapping("/")
    private List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException {
        return restaurantService.getRestaurants();
    }

    /**
     * <p>
     * checks the restaurant table whether it is soft deleted or not by using the restaurant id.
     * if it is present, return that restaurant of the given id.
     * </p>
     *
     * @param id - a restaurant id for which the restaurant to be returned
     * @return   - the restaurant if the restaurant id is found
     * @throws FoodDeliveryManagementException - if the restaurant is not found for the given id
     */
    @GetMapping("/{id}")
    private RestaurantDetailDTO getRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
        return restaurantService.getRestaurantById(id);
    }

    /**
     * <p>
     * checks the restaurant table whether it is soft deleted or not by using the restaurant id.
     * if it is present, set the isDeleted column as true to make soft delete.
     * </p>
     *
     * @param id - a restaurant id to be soft deleted
     * @return  - the success message if restaurant is deleted successfully
     * @throws FoodDeliveryManagementException - if the restaurant is not found for the given id
     */
    @DeleteMapping("/{id}")
    private String deleteRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
        return restaurantService.deleteRestaurantById(id);
    }

    /**
     * <p>
     * Get the existing restaurant object by using the restaurant id and set the values from the
     * restaurant object which is to be updated.
     * </p>
     *
     * @param  id - a restaurant id to be updated
     * @param restaurantDTO - the restaurant to be updated
     * @return - the updated restaurant
     * @throws FoodDeliveryManagementException - if the restaurant is not found for the given id
     *                                         - if the restaurant is not updated
     */
    @PutMapping("/{id}")
    private RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable int id)
            throws FoodDeliveryManagementException {
        return restaurantService.updateRestaurant(restaurantDTO, id);
    }

    /**
     * <p>
     * To fetch the list of restaurant based on it's name/keyword.
     * </p>
     *
     * @param keyword - an input for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given name/keyword
     */
    @GetMapping("/search/")
    private List<RestaurantDetailDTO> searchRestaurant(@RequestParam String keyword) throws FoodDeliveryManagementException {
        return restaurantService.searchRestaurant(keyword);
    }

    /**
     * <p>
     * To fetch the list of restaurant based on it's location.
     * </p>
     *
     * @param location - the location for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given location
     */
    @GetMapping("/search-by-location/")
    private List<RestaurantDetailDTO> searchRestaurantByLocation(@RequestParam String location) throws FoodDeliveryManagementException {
        return restaurantService.searchRestaurantByLocation(location);
    }

    /**
     * <p>
     * To fetch the list of restaurant based on it's cuisine name.
     * </p>
     *
     * @param cuisine - the cuisine for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given cuisine
     */
    @GetMapping("/search-by-cuisine/")
    private List<RestaurantDetailDTO> searchRestaurantByCuisine(@RequestParam String cuisine) throws FoodDeliveryManagementException {
        return restaurantService.searchRestaurantByCuisine(cuisine);
    }
}
