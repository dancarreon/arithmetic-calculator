package com.calculator.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/*
* Exception Handler that intercepts general exceptions, logs them and sanitize response output
* */
@ControllerAdvice
@Slf4j
@SuppressWarnings("rawtypes")
public class CalculatorExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity resourceNotFoundException(IllegalArgumentException e) {
        log.error("Exception while retrieving resource", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity resourceNotValid(PropertyValueException e) {
        log.error("Request specified is invalid: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request information given");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity invalidRequestException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity missingParameterException(MissingServletRequestParameterException e) {
        log.error("Missing parameter in request", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Required request parameter is missing");
    }
}
