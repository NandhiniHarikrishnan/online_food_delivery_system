package com.ideas2it.fooddeliverymanagement.service.impl;

import com.ideas2it.fooddeliverymanagement.dto.CategoryDTO;
import com.ideas2it.fooddeliverymanagement.dto.FoodDTO;
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

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        categoryDTO.setCode(generateCategoryCode(categoryDTO));
        return CategoryMapper.convertIntoDTO(categoryRepository
                .save(CategoryMapper.convertIntoEntity(categoryDTO)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategoryDTO> getCategories() throws FoodDeliveryManagementException {
        List<CategoryDTO> categories = CategoryMapper
                .convertIntoCategoriesDto(categoryRepository.findAll());
        if (categories.isEmpty()) {
            throw new FoodDeliveryManagementException("NO_RECORD_FOUND", HttpStatus.NOT_FOUND);
        }
        return categories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoryDTO getCategoryById(int id) throws FoodDeliveryManagementException {
        return CategoryMapper.convertIntoDTO(categoryRepository.findById(id).orElseThrow(
                () -> new FoodDeliveryManagementException("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteCategoryById(int id) throws FoodDeliveryManagementException {
        if (!categoryRepository.existsById(id)) {
            throw new FoodDeliveryManagementException("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(id);
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            throw new FoodDeliveryManagementException("DELETE_UNSUCCESSFUL", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "deleted successfully " + id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updateCategory(CategoryDTO categoryDTO, int id) throws FoodDeliveryManagementException {
        String message = null;
        if (categoryRepository.existsById(id)) {
            CategoryDTO existingCategory = getCategoryById(id);
            if (null != existingCategory) {
                existingCategory.setName(categoryDTO.getName());
                if (null != categoryDTO.getFoods()) {
                    List<FoodDTO> input = existingCategory.getFoods();
                    input.addAll(categoryDTO.getFoods());
                    existingCategory.setFoods(input);
                }
                categoryRepository.save(CategoryMapper.convertIntoEntity(existingCategory));
                message = categoryDTO.getName() + " Update Successfully";
            }
        } else {
            throw new FoodDeliveryManagementException("CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
        return message;
    }

    /**
     * <p>
     * Generate the category code as per the count of the categories
     * </p>
     *
     * @return an category code with prefix as first three characters of name in upper case
     */
    public String generateCategoryCode(CategoryDTO categoryDTO) {
        Long categoryCode = categoryRepository.getCategoriesCount();
        return categoryDTO.getName().substring(0,3).toUpperCase() + "-" + (++categoryCode);
    }

}