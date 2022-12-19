package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * It's a DTO that represents a role
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-16-10
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {

    private int id;
    private String name;

    private String code;

    private List<UserDTO> userDTOS = new ArrayList<>();
}
