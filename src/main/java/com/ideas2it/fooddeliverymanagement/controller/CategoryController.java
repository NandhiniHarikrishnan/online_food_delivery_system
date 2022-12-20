package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.util.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

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
     * @return - the created category with HttpStatus.CREATED
     */
    @PostMapping("/")
    private CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws FoodDeliveryManagementException {
        return categoryService.addCategory(categoryDTO);
    }

    /**
     * <p>
     * To display all the categories stored in the category table.
     * </p>
     *
     * @return - the list of categories
     * @throws FoodDeliveryManagementException - when employees table is empty
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
     * @return   - the employee if the category id is found
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
     * @return   - the success message with HttpStatus.Ok
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
