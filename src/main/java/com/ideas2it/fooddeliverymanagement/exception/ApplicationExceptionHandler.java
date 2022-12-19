package com.ideas2it.fooddeliverymanagement.exception;

import com.ideas2it.fooddeliverymanagement.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Handles the exception thrown by Food Delivery Management Application
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 14-DEC-2022
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
    public ResponseEntity<ErrorResponse> handleEmployeeManagementException(FoodDeliveryManagementException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatus(),exception.getMessage());
        return new ResponseEntity<> (errorResponse, exception.getStatus());
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String,String> errorResponse = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errorResponse.put(error.getField(), error.getDefaultMessage()));
        return errorResponse;

    }
}
