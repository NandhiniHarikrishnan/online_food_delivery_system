package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.model.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private int id;
    private String code;
    @NotNull(message = "Category name should not be null or empty")
    @Pattern(regexp = "[^\\s][a-zA-Z\\s]*", message = "only alphabets and space are allowed")
    private String name;
    private List<FoodDTO> foods;
}
