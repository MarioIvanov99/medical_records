package com.hospital.medical_records.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.LinkedHashMap;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(ObjectNotFoundException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.NOT_FOUND));
        responseBody.put("detail", exception.getMessage());
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("type", "Validation");
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.BAD_REQUEST));
        responseBody.put("detail", exception.getMessage());
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("type", "ConstraintViolation");
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.BAD_REQUEST));
        responseBody.put("detail", exception.getMessage());
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
              "Authentication Failed!", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<Object> handleInvalidDateRangeException(InvalidDateRangeException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("type", "InvalidDateRange");
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.BAD_REQUEST));
        responseBody.put("detail", exception.getMessage());

        return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
