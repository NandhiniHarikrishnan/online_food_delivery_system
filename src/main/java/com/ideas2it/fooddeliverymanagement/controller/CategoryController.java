package com.ideas2it.fooddeliverymanagement.controller;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.exception.FoodDeliveryManagementException;
import com.ideas2it.fooddeliverymanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * <p>
     * To Create the category.
     * </p>
     *
     * @param categoryDTO - the category to be created
     * @return - the created category with HttpStatus.CREATED
     */
    @PostMapping("/")
    private ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.addCategory(categoryDTO), HttpStatus.CREATED);
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
    public ResponseEntity<List<CategoryDTO>> getCategories() throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getCategories());
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
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int id) throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.FOUND).body(categoryService.getCategoryById(id));
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
    public ResponseEntity<String> deleteCategoryById(@PathVariable int id) throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategoryById(id));
    }

    /**
     * <p>
     * To update the category for the given category id.
     * </p>
     *
     * @param  id - a category id to be updated
     * @param categoryDTO - the category to be updated
     * @return    - the success message if it is updated
     * @throws FoodDeliveryManagementException - if the category is not found, if the category is not updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id)
            throws FoodDeliveryManagementException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryDTO, id));
    }
}
