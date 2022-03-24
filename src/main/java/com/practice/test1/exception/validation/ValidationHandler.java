package com.practice.test1.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails validationErrorHandler(MethodArgumentNotValidException exception,
                                               HttpServletRequest request) {
        ErrorDetails error = ErrorDetails.builder()
                .path(request.getServletPath())
                .timestamp(new Timestamp(new Date().getTime()))
                .build();

        BindingResult bindingResult = exception.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        bindingResult.getAllErrors().forEach((er) -> {
            String field = ((FieldError) er).getField();
            errors.put(field, er.getDefaultMessage());
        });

        error.setValidationErorr(errors);
        return error;
    }
}
