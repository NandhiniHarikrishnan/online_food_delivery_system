package com.ideas2it.fooddeliverymanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {

    private int id;
    private String name;

    private String code;

    private List<UserDTO> userDTOS = new ArrayList<>();
}
