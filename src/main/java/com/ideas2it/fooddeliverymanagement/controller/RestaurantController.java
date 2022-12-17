package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDetailDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/")
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantDetailDTO>> getRestaurants() throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantService.getRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailDTO> getRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantService.getRestaurantById(id));
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
    public ResponseEntity<String> deleteRestaurantById(@PathVariable int id) throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.deleteRestaurantById(id));
    }

    /**
     * <p>
     * To update the restaurant for the given restaurant id.
     * </p>
     *
     * @param  id - a restaurant id to be updated
     * @param restaurantDTO - the restaurant to be updated
     * @return    - the success message if it is updated
     * @throws FoodDeliveryManagementException - if the restaurant is not found, if the restaurant is not updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable int id)
            throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.updateRestaurant(restaurantDTO, id));
    }
}
