/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * It holds the userName and password that the user enters in the login form
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-20
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {

    private String userName;
    private String password;

}
