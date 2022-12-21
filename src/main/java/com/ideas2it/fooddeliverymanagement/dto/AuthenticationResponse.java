package com.ideas2it.fooddeliverymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * For each request it contains JSON token for a response
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-20
 */
@Getter
@AllArgsConstructor
public class AuthenticationResponse {

    private final String jsonToken;
}
