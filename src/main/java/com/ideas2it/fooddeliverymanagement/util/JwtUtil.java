/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * It has a method to generate a JWT after it makes sure the user is valid,
 * and it has a method that will validate a JWT
 * and, if it's valid, return the username contained in it
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-19
 */
@Component
public class JwtUtil {

    private final String Secret_Key = Constants.SECRET_CODE;


    /**
     * The function takes a userDetails object and returns a token
     *
     * @param userDetails This is the user object that contains the user's information.
     * @return A token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * It creates a token with the claims, username, issued date, expiration date and
     * signs it with the secret key.
     *
     * @param claims A map of claims that will be added to the JWT.
     * @param userName The user-name of the user who is requesting the token.
     * @return A JWT token
     */
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(120)))
                .signWith(SignatureAlgorithm.HS256,Secret_Key).compact();
    }

    /**
     * Extract the claims from the token and return the result of applying the claimsResolver function to the claims.
     *
     * @param token The JWT token
     * @param claimsResolver A function that takes in a Claims object and returns a value of your choice.
     * @return A function that takes a Claims object and returns a generic type T.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * It takes a token and returns the claims
     *
     * @param token The token that needs to be validated.
     * @return The JWT token is being returned.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(Secret_Key).parseClaimsJws(token).getBody();
    }

    /**
     * It takes a token and a function as parameters and then extracts the claims from the
     * token and applies the function to the claims
     *
     * @param token The JWT token
     * @return The subject of the token.
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims ::getSubject);
    }

    /**
     * ExtractClaim() is a generic function that takes a token and a function that takes a Claims object and returns a
     * value of type T. It returns a value of type T.
     *
     * @param token The JWT token
     * @return The expiration date of the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims ::getExpiration);
    }

    /**
     * If the expiration date of the token is before the current date, then the token is expired
     *
     * @param token The token to be validated
     * @return A boolean value.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * It checks if the token is expired or not and if the username in the token matches the username of the userDetails
     * object
     *
     * @param token The token to validate
     * @param userDetails The user details object that contains the user's information.
     * @return A boolean value.
     */
    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
