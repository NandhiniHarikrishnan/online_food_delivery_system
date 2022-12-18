package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.model.Food;
import com.ideas2it.fooddeliverymanagement.model.Restaurant;
import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantFoodMapper {

    /**
     * <p>
     * To convert from entity to DTO for restaurant and food
     * </p>
     *
     * @param restaurantFood - a restaurant and food entity to be converted into DTO.
     * @return   - a restaurant and food DTO.
     */
    public static RestaurantFoodDTO convertIntoDTO(RestaurantFood restaurantFood) {
        RestaurantFoodDTO restaurantFoodDTO = new RestaurantFoodDTO();
        Restaurant restaurant;
        Food food;
        if (null != restaurantFood) {
            restaurantFoodDTO.setId(restaurantFood.getId());
            restaurantFoodDTO.setPrice(restaurantFood.getPrice());
            restaurant = restaurantFood.getRestaurant();
            if (null != restaurant) {
                restaurantFoodDTO.setRestaurant(RestaurantMapper.convertRestaurantDTO(restaurant));
            }
            food = restaurantFood.getFood();
            if (null != food) {
                restaurantFoodDTO.setFood(FoodMapper.convertIntoDTO(food));
            }
        }
        return restaurantFoodDTO;
    }

    /**
     * <p>
     * To convert from DTO to Entity for restaurant and food
     * </p>
     *
     * @param restaurantFoodDTO -a restaurant and food DTO to be converted into entity.
     * @return   - a restaurant and food.
     */
    public static RestaurantFood convertIntoEntity(RestaurantFoodDTO restaurantFoodDTO) {
        RestaurantFood restaurantFood = new RestaurantFood();
        RestaurantDTO restaurantDTO;
        FoodDTO foodDTO;
        if (null != restaurantFoodDTO) {
            restaurantFood.setId(restaurantFoodDTO.getId());
            restaurantFood.setPrice(restaurantFoodDTO.getPrice());
            restaurantDTO = restaurantFoodDTO.getRestaurant();
            if (null != restaurantDTO) {
                restaurantFood.setRestaurant(RestaurantMapper.convertRestaurant(restaurantDTO));
            }
            foodDTO = restaurantFoodDTO.getFood();
            if (null != foodDTO) {
                restaurantFood.setFood(FoodMapper.convertIntoEntity(foodDTO));
            }
        }
        return restaurantFood;
    }

    /**
     * <p>
     * To convert from DTO to Entity for the restaurant and food
     * </p>
     *
     * @param restaurantFoods - the list of restaurant and food DTO to be converted into entity.
     * @return   - the list of restaurant and food DTO.
     */
    public static List<RestaurantFoodDTO> convertIntoRestaurantFoodsDTO(List<RestaurantFood> restaurantFoods) {
        List<RestaurantFoodDTO> restaurantFoodsDTO = null;
        if (null != restaurantFoods) {
            restaurantFoodsDTO = restaurantFoods.stream().map(f -> convertIntoDTO(f)).collect(Collectors.toList());
        }
        return restaurantFoodsDTO;
    }
}
