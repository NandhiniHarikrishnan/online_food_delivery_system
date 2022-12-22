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
     * To Create the category.
     * </p>
     *
     * @param categoryDTO - the category to be created
     * @return - the created category
     */
    @PostMapping("/")
    private CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws FoodDeliveryManagementException {
        return categoryService.addCategory(categoryDTO);
    }

    /**
     * <p>
     * To display all the categories.
     * </p>
     *
     * @return - the list of categories
     * @throws FoodDeliveryManagementException - if there is no categories in the table.
     */
    @GetMapping("/")
    public List<CategoryDTO> getCategories() throws FoodDeliveryManagementException {
        return categoryService.getCategories();
    }

    /**
     * <p>
     * To get the category for the given category id.
     * </p>
     *
     * @param id - a category id for which the category to be returned
     * @return   - the category if the category id is found
     * @throws FoodDeliveryManagementException - if the category id is not found
     */
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) throws FoodDeliveryManagementException {
        return categoryService.getCategoryById(id);
    }

    /**
     * <p>
     * To remove the category for the given category id.
     * </p>
     *
     * @param id - a category id to be removed
     * @return   - the success message if the category is removed
     * @throws FoodDeliveryManagementException - if the category is not found
     */
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable int id) throws FoodDeliveryManagementException {
        return categoryService.deleteCategoryById(id);
    }

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
    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id)
            throws FoodDeliveryManagementException {
        return categoryService.updateCategory(categoryDTO, id);
    }
}
