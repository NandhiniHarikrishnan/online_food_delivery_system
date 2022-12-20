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
 * Perform Authentication and authorization
 *
 * @version 1.0
 * @Since 8 Dec 2022
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
     * Perform authentication operation.
     *
     * @param - authenticationManagerBuilder
     * @throws - Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encodePassword());
    }

    /**
     * perform authorization operation
     *
     * @param - http
     * @throws - Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/user/authentication").permitAll()
                .antMatchers("/user/").permitAll()
                .antMatchers(HttpMethod.POST, "/role/").permitAll()
                .antMatchers(HttpMethod.GET, "/user/userId", "/user/address/{addressId}").hasAnyRole("CUSTOMER","ADMIN")                   //hasAuthority("CUSTOMER")
                .antMatchers(HttpMethod.GET, "/user/", "/user/getAllAddress").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/{userId}", "/user/deleteAddress/{userId}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(HttpMethod.PUT, "/user/", "/user/updateAddress/{userId}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(HttpMethod.POST, "/user/addAddress/{userId}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(HttpMethod.GET,"/restaurant/search").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(HttpMethod.POST,"/restaurant/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/restaurant/","/restaurant/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/restaurant/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/restaurant/{id}").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
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
