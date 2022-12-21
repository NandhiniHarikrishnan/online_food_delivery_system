package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * It's a DTO that represents a role
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-16
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {

    private int id;

    @Pattern(regexp = Constants.REGEX_FOR_NAME)
    private String name;

    private String code;

    private List<UserDTO> userDTOS = new ArrayList<>();
}
