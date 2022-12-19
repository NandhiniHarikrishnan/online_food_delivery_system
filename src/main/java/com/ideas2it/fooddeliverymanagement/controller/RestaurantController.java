package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDetailDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping("/")
    public RestaurantDTO addRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) throws FoodDeliveryManagementException{
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @GetMapping("/")
    public List<RestaurantDetailDTO> getRestaurants() throws FoodDeliveryManagementException {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantDetailDTO getRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
        return restaurantService.getRestaurantById(id);
    }

    /**
     * <p>
     * To remove the restaurant for the given restaurant id.
     * </p>
     *
     * @param id - a restaurant id to be removed
     * @return   - the success message with HttpStatus.Ok
     * @throws FoodDeliveryManagementException - if the restaurant is not found
     */
    @DeleteMapping("/{id}")
    public String deleteRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
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
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable int id)
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
     * @throws FoodDeliveryManagementException - if there is no restaurant based on the given keyword
     */
    @GetMapping("/search")
    public List<RestaurantDetailDTO> searchEmployees(@RequestParam String keyword) throws FoodDeliveryManagementException {
        return restaurantService.searchRestaurant(keyword);
    }
}
