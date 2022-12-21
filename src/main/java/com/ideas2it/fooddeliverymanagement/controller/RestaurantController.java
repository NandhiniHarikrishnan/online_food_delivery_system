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
 * It exposes endpoints for the admin to perform CRUD operations on the restaurant.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-15
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * <p>
     * To add the new restaurant.
     * </p>
     *
     * @param restaurantDTO - the restaurant to be created
     * @return - the created restaurant.
     * @throws FoodDeliveryManagementException - if the restaurant is already exist
     */
    @PostMapping("/")
    private RestaurantDTO addRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException{
        return restaurantService.addRestaurant(restaurantDTO);
    }

    /**
     * <p>
     * To display all the restaurants.
     * </p>
     *
     * @return - the list of restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants in the table.
     */
    @GetMapping("/")
    private List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException {
        return restaurantService.getRestaurants();
    }

    /**
     * <p>
     * To get the restaurant for the given restaurant id.
     * </p>
     *
     * @param id - a restaurant id for which the restaurant to be returned
     * @return   - the restaurant if the restaurant id is found
     * @throws FoodDeliveryManagementException - if the restaurant id is not found
     */
    @GetMapping("/{id}")
    private RestaurantDetailDTO getRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
        return restaurantService.getRestaurantById(id);
    }

    /**
     * <p>
     * To remove the restaurant for the given restaurant id.
     * </p>
     *
     * @param id - a restaurant id to be removed
     * @return   - the success message if the restaurant is deleted
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @DeleteMapping("/{id}")
    private String deleteRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
        return restaurantService.deleteRestaurantById(id);
    }

    /**
     * <p>
     * To update the restaurant for the given restaurant id.
     * </p>
     *
     * @param  id - a restaurant id to be updated
     * @param restaurantDTO - the restaurant to be updated
     * @return    - the updated restaurant
     * @throws FoodDeliveryManagementException - if the restaurant is not found, if the restaurant is not updated
     */
    @PutMapping("/{id}")
    private RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable int id)
            throws FoodDeliveryManagementException {
        return restaurantService.updateRestaurant(restaurantDTO, id);
    }

    /**
     * <p>
     * To search the restaurant.
     * </p>
     *
     * @param keyword - an input for which restaurant will be filtered
     * @return - the list of filtered restaurants
     * @throws FoodDeliveryManagementException - if there is no restaurants based on the given keyword
     */
    @GetMapping("/search/")
    private List<RestaurantDetailDTO> searchRestaurant(@RequestParam String keyword) throws FoodDeliveryManagementException {
        return restaurantService.searchRestaurant(keyword);
    }

    /**
     * <p>
     * To search the restaurant based on it's location.
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
     * To search the restaurant based on it's cuisine.
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
