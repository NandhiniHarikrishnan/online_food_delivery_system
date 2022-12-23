/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.dto.RestaurantFoodDTO;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.FoodMapper;
import com.ideas2it.fooddeliverymanagement.model.Food;
import com.ideas2it.fooddeliverymanagement.repository.FoodRepository;
import com.ideas2it.fooddeliverymanagement.service.FoodService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * This class is a service that provides access to the `Food` entity
 * </p>
 *
 * @author Jeevanantham
 * @version 1.0
 * @since 13-DEC-2022
 */
@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     *  {@inheritDoc}
     */
    public List<FoodDTO> searchFood(String keyword) throws FoodDeliveryManagementException {
        List<Food> foods = foodRepository.searchFood(keyword);
        if (!foods.isEmpty()) {
            return FoodMapper.convertIntoFoodsDTO(foods);
        } else {
            throw new FoodDeliveryManagementException(Constants.NO_RECORD_FOUND + keyword, HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public String deleteFoodById(int id) throws FoodDeliveryManagementException{
        if (!foodRepository.existsById(id)){
            throw new FoodDeliveryManagementException(Constants.NO_RECORD_FOUND+ id, HttpStatus.NOT_FOUND);
        }
        foodRepository.deleteById(id);
        return Constants.DELETED_SUCCESSFULLY +id;
    }

    /**
     *  {@inheritDoc}
     */
    public FoodDTO updateFoodById(FoodDTO foodDTO, int id) throws FoodDeliveryManagementException{
        if(foodRepository.existsById(id)) {
            FoodDTO existingFoodDTO= getFoodById(id);
            existingFoodDTO.setName(foodDTO.getName());
            existingFoodDTO.setWeight(foodDTO.getWeight());
            existingFoodDTO.setDescription(foodDTO.getDescription());
            CategoryDTO categoryDTO = existingFoodDTO.getCategory();
            if(null != categoryDTO) {
                existingFoodDTO.setCategory(foodDTO.getCategory());
            }
            List<RestaurantFoodDTO> restaurantFoods = existingFoodDTO.getRestaurantFoods();
            if (null != restaurantFoods) {
                List<RestaurantFoodDTO> existingFoodDTORestaurantFoods = existingFoodDTO.getRestaurantFoods();
                existingFoodDTORestaurantFoods.addAll(restaurantFoods);
                existingFoodDTO.setRestaurantFoods(existingFoodDTORestaurantFoods);
            }
            return FoodMapper.convertIntoDTO(
                    foodRepository.save(FoodMapper.convertIntoEntity(existingFoodDTO)));
        }
        else {
            throw new FoodDeliveryManagementException(Constants.NO_RECORD_FOUND + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  {@inheritDoc}
     */
    public FoodDTO getFoodById(int id) throws FoodDeliveryManagementException {
        Optional<Food> optionalFood = foodRepository.findById(id);
        if(optionalFood.isEmpty()) {
            throw new FoodDeliveryManagementException(Constants.NO_RECORD_FOUND + id, HttpStatus.NOT_FOUND);
        }
        return FoodMapper.convertIntoDTO(optionalFood.get());
    }

    /**
     *  {@inheritDoc}
     */
    public List<FoodDTO> searchFoodByCategory(String name) throws FoodDeliveryManagementException {
        List<Food> foods = foodRepository.searchFoodByCategory(name);
        if (!foods.isEmpty()) {
            return FoodMapper.convertIntoFoodsDTO(foods);
        } else {
            throw new FoodDeliveryManagementException(Constants.NO_RECORD_FOUND + name, HttpStatus.NOT_FOUND);
        }
    }
}

