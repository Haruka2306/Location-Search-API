package com.example.crudapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice
public class LocationSearchExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> badRequestException(ConstraintViolationException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCornerFoundException.class)
    public ResponseEntity<Map<String, String>> badRequestException(NoCornerFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateCornerException.class)
    public ResponseEntity<Map<String, String>> badRequestException(DuplicateCornerException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }
}
