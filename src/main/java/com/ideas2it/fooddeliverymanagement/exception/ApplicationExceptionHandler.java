/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.exception;

import com.ideas2it.fooddeliverymanagement.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Handles the exception thrown by Food Delivery Management Application
 * </p>
 *
 * @author Naganandhini
 * @version 1.0
 * @since - 2022-12-14
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * <p>
     * To handle the FoodDeliveryManagementException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status and code
     */
    @ExceptionHandler(FoodDeliveryManagementException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmployeeManagementException(FoodDeliveryManagementException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return new ResponseEntity<> (errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * To handle the ResourceNotFoundException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status and code
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.NOT_FOUND,exception.getMessage());
        return new ResponseEntity<> (errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     * To handle the ResourceExistException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status and code
     */
    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceExistException(ResourceExistException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.CONFLICT,exception.getMessage());
        return new ResponseEntity<> (errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * <p>
     * To handle the MethodArgumentNotValidException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String,String> errorResponse = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errorResponse.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<> (errorResponse, HttpStatus.BAD_REQUEST);

    }

    /**
     * <p>
     * To handle the HttpRequestMethodNotSupportedException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status and code
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.METHOD_NOT_ALLOWED, exception.getMessage());
        return new ResponseEntity<> (errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * <p>
     * To handle the SignatureException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status and code
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponseDTO> handleSignatureException(SignatureException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.FORBIDDEN, exception.getMessage());
        return new ResponseEntity<> (errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * <p>
     * To handle the MethodArgumentTypeMismatchException.
     * </p>
     *
     * @param exception - an exception to be handled
     * @return - the error message with respective status
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<> (errorResponse, HttpStatus.BAD_REQUEST);
    }
}
