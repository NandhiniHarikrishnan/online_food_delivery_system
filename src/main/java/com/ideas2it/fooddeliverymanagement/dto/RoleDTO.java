/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Contain the fields of the role id, name, code, user's.
 * The name field in role must be in the regex pattern.
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

    @Pattern(regexp = Constants.REGEX_FOR_NAME, message = Constants.INVALID_NAME)
    private String name;

    private String code;

    private List<UserDTO> userDTOS = new ArrayList<>();
}
