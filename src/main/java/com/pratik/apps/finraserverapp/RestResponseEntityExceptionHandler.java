package com.pratik.apps.finraserverapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleValidationException(
            RuntimeException ex, WebRequest request) {
        String responseBody = ex.getLocalizedMessage();
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleGeneralException(
            RuntimeException ex, WebRequest request) {
        String responseBody = ex.getLocalizedMessage();
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
