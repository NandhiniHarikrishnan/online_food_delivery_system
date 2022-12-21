package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * It is a category DTO that contains fields which is shown to the user
 * for the category entity.
 *
 * @author - Naganandhini
 * @version - 1.0
 * @since - 2022-12-10
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private int id;
    private String code;
    @NotEmpty(message = Constants.NOT_NULL_OR_EMPTY_NAME)
    @Pattern(regexp = Constants.REGEX_FOR_CATEGORY, message = Constants.ONLY_ALPHABETS_SPACE)
    private String name;
    private String food;
    private List<FoodDTO> foods;
}
