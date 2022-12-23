/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object that contains the user id, name, user-name, email,
 * password and addresses.
 * Each and every field must be validated.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private int id;

    @Pattern(regexp = Constants.REGEX_FOR_NAME, message = Constants.INVALID_NAME)
    private String name;

    @Pattern(regexp = Constants.REGEX_FOR_USERNAME, message = Constants.INVALID_USERNAME)
    private String userName;

    @Email(message = Constants.INVALID_EMAIL)
    private String email;

    @NotBlank(message = Constants.PLEASE_MENTION_PASSWORD)
    private String password;

    private List<AddressDTO> addresses = new ArrayList<>();

    private List<RoleDTO> roles = new ArrayList<>();
}
