package com.example.market_web.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(new Date())
                .generalMessage("An error has occurred in one of the market-web-ms services, please check the input data.")
                .details(ex.getMessage())
                .message(request.getDescription(false))
                .build(), HttpStatus.BAD_REQUEST);
    }
}
