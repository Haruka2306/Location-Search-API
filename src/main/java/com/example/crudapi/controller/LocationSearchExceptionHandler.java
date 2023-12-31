package com.example.crudapi.controller;

import com.example.crudapi.exception.DuplicateCornerException;
import com.example.crudapi.exception.NoCornerFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class LocationSearchExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError e : ex.getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }
        Map<String, Object> body = Map.of(
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", errors
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(NoCornerFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoCornerFoundException(NoCornerFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCornerException.class)
    public ResponseEntity<Map<String, String>> handleDuplicationCornerException(DuplicateCornerException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "status", String.valueOf(HttpStatus.CONFLICT.value()),
                "error", HttpStatus.CONFLICT.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.CONFLICT);
    }
}
