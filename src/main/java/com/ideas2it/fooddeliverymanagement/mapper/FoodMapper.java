package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.model.Category;
import com.ideas2it.fooddeliverymanagement.model.Food;
import com.ideas2it.fooddeliverymanagement.model.RestaurantFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Handles the converting process from entity to DTO for food.
 * </p
 *
 * @author Naganandhini
 * @version 1.0 13-DEC-2022
 */
@Component
public class FoodMapper {

    /**
     * <p>
     * To convert from entity to DTO for food
     * </p>
     *
     * @param food - a food entity to be converted into DTO.
     * @return   - a food DTO.
     */
    public static FoodDTO convertIntoDTO(Food food) {
        Category category;
        List<RestaurantFood> restaurantFoods;
        List<RestaurantFoodDTO> restaurantFoodsDTO = null;
        FoodDTO foodDTO = new FoodDTO();
        if (null != food) {
            foodDTO.setId(food.getId());
            foodDTO.setName(food.getName());
            foodDTO.setWeight(food.getWeight());
            foodDTO.setDescription(food.getDescription());
            foodDTO.setAvailable(food.isAvailable());
            category = food.getCategory();
            if (null != category) {
                category.setFoods(null);
                foodDTO.setCategory(CategoryMapper.convertIntoDTO(category));
            }
            restaurantFoods = food.getRestaurantFoods();
            if(null != restaurantFoods) {
                restaurantFoodsDTO = restaurantFoods.stream().map(r -> {
                    r.setFood(null);
                    return RestaurantFoodMapper.convertIntoDTO(r);
                }).collect(Collectors.toList());
                foodDTO.setRestaurantFoods(restaurantFoodsDTO);
            }
        }
        return foodDTO;
    }

    /**
     * <p>
     * To convert from DTO to Entity for food
     * </p>
     *
     * @param foodDTO -a food DTO to be converted into entity.
     * @return   - a food.
     */
    public static Food convertIntoEntity(FoodDTO foodDTO) {
        CategoryDTO categoryDTO;
        List<RestaurantFood> restaurantFoods = null;
        List<RestaurantFoodDTO> restaurantFoodsDTO;
        Food food = new Food();
        if (null != foodDTO) {
            food.setId(foodDTO.getId());
            food.setName(foodDTO.getName());
            food.setWeight(foodDTO.getWeight());
            food.setDescription(foodDTO.getDescription());
            food.setAvailable(foodDTO.isAvailable());
            categoryDTO = foodDTO.getCategory();
            if (null != categoryDTO) {
                categoryDTO.setFoods(null);
                food.setCategory(CategoryMapper.convertIntoEntity(categoryDTO));
            }
            restaurantFoodsDTO = foodDTO.getRestaurantFoods();
            if(null != restaurantFoodsDTO) {
                restaurantFoods = restaurantFoodsDTO.stream().map(rDTO -> {
                    rDTO.setFood(null);
                    return RestaurantFoodMapper.convertIntoEntity(rDTO);
                }).collect(Collectors.toList());
                food.setRestaurantFoods(restaurantFoods);
            }
        }
        return food;
    }

    /**
     * <p>
     * To convert from DTO to Entity for the list of foods
     * </p>
     *
     * @param foods - the list of foods DTO to be converted into entity.
     * @return   - the list of foods DTO.
     */
    public static List<FoodDTO> convertIntoFoodsDTO(List<Food> foods) {
        List<FoodDTO> foodsDTO = null;
        if (null != foods) {
            foodsDTO = foods.stream().map(f -> convertIntoDTO(f)).collect(Collectors.toList());
        }
        return foodsDTO;
    }
}
