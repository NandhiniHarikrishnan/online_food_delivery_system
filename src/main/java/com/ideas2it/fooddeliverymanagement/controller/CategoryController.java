package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * It exposes endpoints for the admin to perform CRUD operations on the category.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-14
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

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
    @PostMapping("/")
    private CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws FoodDeliveryManagementException {
        return categoryService.addCategory(categoryDTO);
    }

    /**
     * <p>
     * To display all the existing categories stored in the category table.
     * </p>
     *
     * @return - the list of existing categories
     * @throws FoodDeliveryManagementException - if there is no categories in the category table
     */
    @GetMapping("/")
    public List<CategoryDTO> getCategories() throws FoodDeliveryManagementException {
        return categoryService.getCategories();
    }

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
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) throws FoodDeliveryManagementException {
        return categoryService.getCategoryById(id);
    }

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
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable int id) throws FoodDeliveryManagementException {
        return categoryService.deleteCategoryById(id);
    }

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
    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id)
            throws FoodDeliveryManagementException {
        return categoryService.updateCategory(categoryDTO, id);
    }
}
