package com.ideas2it.fooddeliverymanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.Date;

/**
 * <p>
 * Error Response of Food Delivery Management application
 * </p>
 *
 * @author Naganandhini
 * @version 1.0 14-DEC-2022
 */
@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date errorOccuredDate;

    private int code;

    private String status;

    private String message;

    public ErrorResponse() {
        errorOccuredDate = new Date();
    }

    public ErrorResponse(
            HttpStatus httpStatus,
            String message
    ) {
        this();

        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }
}