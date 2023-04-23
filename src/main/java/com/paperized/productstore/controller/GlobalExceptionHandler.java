package com.paperized.productstore.controller;

import com.paperized.productstore.exception.ApiError;
import com.paperized.productstore.exception.EntityAlreadyExistsException;
import com.paperized.productstore.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundExceptionHandling(Exception exception) {
        return new ResponseEntity<>(ApiError.fromErrors(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ApiError> entityAlreadyExistsExceptionHandling(Exception exception) {
        return new ResponseEntity<>(ApiError.fromErrors(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
