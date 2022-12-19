package com.ideas2it.fooddeliverymanagement.util;


public class Constants {
    public static final String REGEX_FOR_NAME = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){1,18}[a-zA-Z0-9]$";
    public static final String REGEX_FOR_PHONE_NUMBER = "^[6-9]{1}[0-9]{9}";
    public static final String REGEX_FOR_PINC0DE= "^[1-9][0-9]{5}$";
    public static final String INVALID_NAME = "Invalid Name";
    public static final String INVALID_EMAIL = "Invalid Email";
    public static final String ADDRESS_NOT_EMPTY = "Please Mention Your Address";
    public static final String MENTION_STREET_NAME = "Please Mention Your Street Name";
    public static final String MENTION_CITY_NAME = "Please Mention City Name";
    public static final String MENTION_DISTRICT_NAME = "Please Mention District Name";
    public static final String MENTION_STATE_NAME = "Please Mention State Name";
    public static final String MENTION_PLOT_NUMBER = "Please Mention Plot Number";
    public static final String ADDRESS_NOT_ADDED = "Address Not Added";
    public static final String ADDRESS_NOT_FOUND = "Address Not Found";
    public static final String ADDRESS_NOT_UPDATED ="Address Not Updated";
    public static final String ADDRESS_NOT_DELETED = "Address Not Deleted";
    public static final String ROLE_NOT_ADDED = "Role Not Added";
    public static final String ROLE_NOT_FOUND = "Role Not Found";
    public static final String ROLE_NOT_DELETED = "Role Not Added";
    public static final String EMAIL_ALREADY_EXIST = "Email Is Already Exist";
    public static final String USER_NOT_ADDED = "User Not Added";
    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String DETAILS_NOT_UPDATED = "Details Not Updated";
}