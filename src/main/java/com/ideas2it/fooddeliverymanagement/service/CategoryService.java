package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;

import java.util.List;

/**
 * Perform create, read, update, delete (CRUD) operation for the restaurant.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-14
 */
public interface CategoryService {

    /**
     * <p>
     * To Create the category.
     * </p>
     *
     * @param categoryDTO - the category to be created
     * @return - the created category
     */
    CategoryDTO addCategory(CategoryDTO categoryDTO) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To display all the categories stored in the category table.
     * </p>
     *
     * @return - the list of categories
     * @throws FoodDeliveryManagementException - when employees table is empty
     */
    List<CategoryDTO> getCategories() throws FoodDeliveryManagementException;

    /**
     * <p>
     * To get the category for the given category id.
     * </p>
     *
     * @param id - a category id for which the category to be returned
     * @return   - the employee if the category id is found
     * @throws FoodDeliveryManagementException - if the category id is not found
     */
    CategoryDTO getCategoryById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To soft delete the category for the given category id.
     * </p>
     *
     * @param id - a category id to be removed
     * @return   - the success message with HttpStatus.Ok
     * @throws FoodDeliveryManagementException - if the category is not found
     */
    String deleteCategoryById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To update the category for the given category id.
     * </p>
     *
     * @param  id - a category id to be updated
     * @param categoryDTO - the category to be updated
     * @return    - the updated category
     * @throws FoodDeliveryManagementException - if the category is not found, if the category is not updated
     */
    CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) throws FoodDeliveryManagementException;

}
