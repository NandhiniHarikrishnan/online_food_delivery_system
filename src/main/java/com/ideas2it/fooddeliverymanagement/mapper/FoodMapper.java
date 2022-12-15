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
public class FoodMapper {

    private CategoryMapper categoryMapper;

    /**
     * <p>
     * To convert from entity to DTO for food
     * </p>
     *
     * @param food - a food entity to be converted into DTO.
     * @return   - a food DTO.
     */
    public FoodDTO convertIntoDTO(Food food) {
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
            }
            foodDTO.setCategory(categoryMapper.convertIntoDTO(category));
            restaurantFoods = food.getRestaurantFoods();
            if(null != restaurantFoods) {
                restaurantFoodsDTO = restaurantFoods.stream().map(r -> {
                    r.setFood(null);
                    RestaurantFoodDTO rDTO = new RestaurantFoodDTO();
                    //need to add restaurant DTO
                    rDTO.setPrice(r.getPrice());
                    return rDTO;
                }).collect(Collectors.toList());
            }
            foodDTO.setRestaurantFoods(restaurantFoodsDTO);
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
    public Food convertIntoEntity(FoodDTO foodDTO) {
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
            }
            food.setCategory(categoryMapper.convertIntoEntity(categoryDTO));
            restaurantFoodsDTO = foodDTO.getRestaurantFoods();
            if(null != restaurantFoodsDTO) {
                restaurantFoods = restaurantFoods.stream().map(rDTO -> {
                    rDTO.setFood(null);
                    RestaurantFood r = new RestaurantFood();
                    r.setPrice(rDTO.getPrice());
                    return r;
                }).collect(Collectors.toList());
            }
            food.setRestaurantFoods(restaurantFoods);
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
    public List<FoodDTO> convertIntoFoodsDTO(List<Food> foods) {
        List<FoodDTO> foodsDTO = null;
        if (null != foods) {
            foodsDTO = foods.stream().map(f -> convertIntoDTO(f)).collect(Collectors.toList());
        }
        return foodsDTO;
    }
}
