package com.example.market_web.commons.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ErrorDetails {
    private Date timestamp;
    private String generalMessage;
    private String message;
    private String details;
}
