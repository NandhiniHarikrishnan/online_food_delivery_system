/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.mapper.CategoryMapper;
import com.ideas2it.fooddeliverymanagement.model.Category;
import com.ideas2it.fooddeliverymanagement.repository.CategoryRepository;
import com.ideas2it.fooddeliverymanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * It performs create, read, update, delete (CRUD) operation for the category and
 * throws custom exception if the category is not present in the database
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) throws FoodDeliveryManagementException {
        CategoryDTO createdCategoryDTO;
        if (null == categoryRepository.findByName(categoryDTO.getName())) {
            categoryDTO.setCode(generateCategoryCode(categoryDTO));
            createdCategoryDTO = CategoryMapper.convertIntoDTO(categoryRepository
                    .save(CategoryMapper.convertIntoEntity(categoryDTO)));
            if(null == createdCategoryDTO) {
                throw new FoodDeliveryManagementException(Constants.CATEGORY_NOT_ADDED, HttpStatus.NOT_FOUND);
            }
        }
        else {
            throw new FoodDeliveryManagementException(Constants.CATEGORY_EXIST, HttpStatus.NOT_FOUND);
        }
        return createdCategoryDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategoryDTO> getCategories() throws FoodDeliveryManagementException {
        List<CategoryDTO> categories = CategoryMapper
                .convertIntoCategoriesDto(categoryRepository.findAll());
        if (categories.isEmpty()) {
            throw new FoodDeliveryManagementException(Constants.NO_RECORD_FOUND, HttpStatus.NOT_FOUND);
        }
        return categories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryDTO getCategoryById(int id) throws FoodDeliveryManagementException {
        return CategoryMapper.convertIntoDTO(categoryRepository.findById(id).orElseThrow(
                () -> new FoodDeliveryManagementException(Constants.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteCategoryById(int id) throws FoodDeliveryManagementException {
        if (!categoryRepository.existsById(id)) {
            throw new FoodDeliveryManagementException(Constants.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(id);
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            throw new FoodDeliveryManagementException(Constants.CATEGORY_NOT_DELETED, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Constants.DELETED_SUCCESSFULLY + id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) throws FoodDeliveryManagementException {
        CategoryDTO updatedCategoryDTO = null;
        if (categoryRepository.existsById(id)) {
            CategoryDTO existingCategory = getCategoryById(id);
            if (null != existingCategory) {
                existingCategory.setName(categoryDTO.getName());
                if (null != categoryDTO.getFoods()) {
                    List<FoodDTO> input = existingCategory.getFoods();
                    input.addAll(categoryDTO.getFoods());
                    existingCategory.setFoods(input);
                }
                updatedCategoryDTO = CategoryMapper.convertIntoDTO(categoryRepository.save(CategoryMapper.convertIntoEntity(existingCategory)));
                if (null == updatedCategoryDTO) {
                    throw new FoodDeliveryManagementException(Constants.CATEGORY_NOT_UPDATED, HttpStatus.NOT_FOUND);
                }
            }
        } else {
            throw new FoodDeliveryManagementException(Constants.CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return updatedCategoryDTO;
    }

    /**
     * <p>
     * Generate the category code as per the count of the categories
     * </p>
     *
     * @return - a generated code with prefix as first three characters of name in upper case
     */
    public String generateCategoryCode(CategoryDTO categoryDTO) {
        Long categoryCode = categoryRepository.getCategoriesCount();
        return categoryDTO.getName().substring(0,3).toUpperCase() + "-" + (++categoryCode);
    }

}