/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.securityConfiguration;

import com.ideas2it.fooddeliverymanagement.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Is used to configure the security of the application and Perform Authentication, authorization
 *
 * @version 1.0
 * @Since 20 Dec 2022
 * @Author Dilip.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtRequestFilter jwtRequestFilter;


    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter, UserDetailsService userDetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * This function is used to configure the AuthenticationManagerBuilder object, which is used to create an
     * AuthenticationManager object
     *
     * @param authenticationManagerBuilder This is the object that is used to create an AuthenticationManager instance
     * which is the main Spring Security interface for authenticating a user.
     * @throws - Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encodePassword());
    }

    /**
     * The above function is used to configure the security of the application. It is used to define the access control of
     * the application.
     *
     * @param http This is the object that allows configuring web based security for specific http requests.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/authentication").permitAll()
                .antMatchers(HttpMethod.POST, "/user/").permitAll()
                .antMatchers(HttpMethod.POST, "/role/").permitAll()
                .antMatchers(HttpMethod.GET, "/user/userId", "/user/address/{addressId}").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/user/", "/user/getAllAddresses").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/getOrderDetails/{userId}").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/{userId}", "/user/deleteAddress/{userId}").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/user/", "/user/updateAddress/{userId}").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/user/addAddress/{userId}").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/restaurant/search/").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/restaurant/").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/restaurant/", "/restaurant/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/restaurant/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/restaurant/search-by-location/").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/restaurant/search-by-cuisine/").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers(HttpMethod.PUT, "/restaurant/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/order/{customerId}").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/order/{id}").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers(HttpMethod.PUT, "/order/assignDelivery/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/food/search/").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/food/search-by-category/").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers(HttpMethod.GET, "/food/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/food/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/food/{id}").hasAuthority("ADMIN")
                .antMatchers("/cuisine/**").hasAuthority("ADMIN")
                .antMatchers("/category/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    /**
     * The function is used to expose the `AuthenticationManager` as a Bean
     *
     * @return AuthenticationManager
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /**
     * Create a new BCryptPasswordEncoder object.
     *
     * @return - BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}
