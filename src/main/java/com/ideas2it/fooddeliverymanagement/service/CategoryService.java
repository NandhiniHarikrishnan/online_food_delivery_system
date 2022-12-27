package com.ideas2it.fooddeliverymanagement.service;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;

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
     * To Create the new category if the given values are valid that is
     * the category name is already exist or not.
     * </p>
     *
     * @param categoryDTO - the category values to be created
     * @return - the created category
     * @throws FoodDeliveryManagementException - if the category name is already exist in the category table.
     */
    CategoryDTO addCategory(CategoryDTO categoryDTO) throws FoodDeliveryManagementException;

    /**
     * <p>
     * To display all the existing categories stored in the category table.
     * </p>
     *
     * @return - the list of existing categories
     * @throws FoodDeliveryManagementException - if there is no categories in the category table
     */
    List<CategoryDTO> getCategories() throws FoodDeliveryManagementException;

    /**
     * <p>
     * checks the category table whether it is soft deleted or not by using the category id.
     * if it is present, return that category of the given id.
     * </p>
     *
     * @param id - a category id for which the category to be returned
     * @return   - the category if the category id is found
     * @throws FoodDeliveryManagementException - if the category is not found for the given id
     */
    CategoryDTO getCategoryById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * checks the category table whether it is soft deleted or not by using the category id.
     * if it is present, set the isDeleted column as true to make soft delete.
     * </p>
     *
     * @param id - a category id to be soft deleted
     * @return  - no content found with status code
     * @throws FoodDeliveryManagementException - if the category is not found for the given id
     */
    String deleteCategoryById(int id) throws FoodDeliveryManagementException;

    /**
     * <p>
     * Get the existing category object by using the category id and set the values from the
     * category object which is to be updated.
     * </p>
     *
     * @param  id - a category id to be updated
     * @param categoryDTO - the category to be updated
     * @return - the updated category
     * @throws FoodDeliveryManagementException - if the category is not found for the given id
     *                                         - if the category is not updated
     */
    CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) throws FoodDeliveryManagementException;

}
