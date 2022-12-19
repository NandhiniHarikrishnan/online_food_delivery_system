package com.ideas2it.fooddeliverymanagement.dto;

import com.ideas2it.fooddeliverymanagement.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object that contains the user's id, name, email, and a list of addresses.
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

    @Email(message = Constants.INVALID_EMAIL)
    private String email;

    @NotBlank(message = Constants.ADDRESS_NOT_EMPTY)
    private List<AddressDTO> addresses = new ArrayList<>();

    private List<RoleDTO> roles = new ArrayList<>();
}
