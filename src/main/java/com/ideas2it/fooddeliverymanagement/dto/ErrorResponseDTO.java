package com.ideas2it.fooddeliverymanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorResponseDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date errorOccuredDate;

    private int code;

    private String status;

    private String message;

    public ErrorResponseDTO() {
        errorOccuredDate = new Date();
    }

    public ErrorResponseDTO(
            HttpStatus httpStatus,
            String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    public ErrorResponseDTO(
            String message) {
        this();
        this.message = message;
    }
}
