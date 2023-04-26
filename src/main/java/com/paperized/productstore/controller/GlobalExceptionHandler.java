package com.paperized.productstore.controller;

import com.paperized.productstore.exception.ApiErrorResponse;
import com.paperized.productstore.exception.EntityAlreadyExistsException;
import com.paperized.productstore.exception.EntityNotFoundException;
import io.jsonwebtoken.JwtException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final Map<String, String> constrainsErrorCodeMap;

  public GlobalExceptionHandler(@Qualifier("constraintsTranslator") Map<String, String> constrainsErrorCodeMap) {
    this.constrainsErrorCodeMap = constrainsErrorCodeMap;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> entityNotFoundExceptionHandling(Exception exception) {
    return new ResponseEntity<>(
      ApiErrorResponse.fromErrors(HttpStatus.NOT_FOUND,
        "ENTITY_NOT_FOUND",
        exception.getMessage()),
      HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<ApiErrorResponse> entityAlreadyExistsExceptionHandling(Exception exception) {
    return new ResponseEntity<>(
      ApiErrorResponse.fromErrors(HttpStatus.CONFLICT,
        "ENTITY_ALREADY_EXISTS",
        exception.getMessage()),
      HttpStatus.CONFLICT
    );
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiErrorResponse> entityNotFoundExceptionHandling(DataIntegrityViolationException exception) {
    if(!(exception.getCause() instanceof ConstraintViolationException violationException)) {
      return internalErrorException();
    }

    String errorCode = constrainsErrorCodeMap.getOrDefault(violationException.getConstraintName(), "UNHANDLED");
    return new ResponseEntity<>(
      ApiErrorResponse.fromErrors(HttpStatus.CONFLICT,
        errorCode,
        "Field already in use"),
      HttpStatus.CONFLICT
    );
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApiErrorResponse> authenticationExceptionHandling(Exception exception) {
    HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
    String errorCode, errorMessage;

    if (exception instanceof BadCredentialsException) {
      httpStatus = HttpStatus.BAD_REQUEST;
      errorCode = "BAD_CREDENTIALS";
      errorMessage = "Invalid username or password";
    } else if (exception instanceof LockedException) {
      errorCode = "LOCKED_ACCOUNT";
      errorMessage = "User account is locked";
    } else if (exception instanceof DisabledException) {
      errorCode = "DISABLED_ACCOUNT";
      errorMessage = "User account is disabled";
    } else if (exception instanceof CredentialsExpiredException) {
      errorCode = "CREDENTIALS_EXPIRED";
      errorMessage = "User credentials have expired";
    } else if (exception instanceof JwtException) {
      errorCode = "INVALID_TOKEN";
      errorMessage = "Invalid or expired token";
    } else {
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      errorCode = "INTERNAL";
      errorMessage = "An unexpected error occurred";
    }

    return new ResponseEntity<>(ApiErrorResponse.fromErrors(httpStatus, errorCode, errorMessage), httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> internalErrorException() {
    return new ResponseEntity<>(
      ApiErrorResponse.fromErrors(HttpStatus.INTERNAL_SERVER_ERROR,
        "INTERNAL_ERROR",
        "An unexpected error occurred"),
      HttpStatus.INTERNAL_SERVER_ERROR
    );
  }
}
