package com.ideas2it.fooddeliverymanagement.mapper;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.model.Category;
import com.ideas2it.fooddeliverymanagement.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * Handles the converting process from entity to DTO for category.
 * </p
 *
 * @author Naganandhini
 * @version 1.0 13-DEC-2022
 */

public class CategoryMapper {

    private FoodMapper foodMapper = new FoodMapper();

    /**
     * <p>
     * To convert from entity to DTO for category
     * </p>
     *
     * @param category - a category entity to be converted into DTO.
     * @return   - a category DTO.
     */
    public CategoryDTO convertIntoDTO(Category category) {
        List<Food> foods;
        List<FoodDTO> foodsDTO = null;
        CategoryDTO categoryDTO = new CategoryDTO();
        if(null != category) {
            categoryDTO.setId(category.getId());
            categoryDTO.setCode(category.getCode());
            categoryDTO.setName(category.getName());
            foods = category.getFoods();
            if(null != foods) {
                foodsDTO = foods.stream().map((Function<Food, FoodDTO>) f -> {
                    f.setCategory(null);
                    return foodMapper.convertIntoDTO(f);
                }).collect(Collectors.toList());
            }
            categoryDTO.setFoods(foodsDTO);
        }
        return categoryDTO;
    }

    /**
     * <p>
     * To convert from DTO to Entity for category
     * </p>
     *
     * @param categoryDTO -a category DTO to be converted into entity.
     * @return   - a category.
     */
    public Category convertIntoEntity(CategoryDTO categoryDTO) {
        List<FoodDTO> foodsDTO;
        List<Food> foods = null;
        Category category = new Category();
        if(null != categoryDTO) {
            category.setId(categoryDTO.getId());
            category.setCode(categoryDTO.getCode());
            category.setName(categoryDTO.getName());
            foodsDTO = categoryDTO.getFoods();
            if(null != foodsDTO) {
                foods = foodsDTO.stream().map(f -> {
                    f.setCategory(null);
                    return foodMapper.convertIntoEntity(f);
                }).collect(Collectors.toList());
            }
            category.setFoods(foods);
        }
        return category;
    }

    /**
     * <p>
     * To convert from DTO to Entity for the list of categories
     * </p>
     *
     * @param categories - the list of categories DTO to be converted into entity.
     * @return   - the list of categories DTO.
     */
    public List<CategoryDTO> convertIntoCategoriesDto(List<Category> categories) {
        List<CategoryDTO> categoriesDTO = null;
        if (null != categories) {
            categoriesDTO = categories.stream().map(c -> convertIntoDTO(c)).collect(Collectors.toList());
        }
        return categoriesDTO;
    }
}
