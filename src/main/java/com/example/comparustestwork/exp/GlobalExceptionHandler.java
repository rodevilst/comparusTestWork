package com.example.comparustestwork.exp;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDbError(DataAccessException e) {
        return ResponseEntity
                .status(503)
                .body(new ErrorResponse("Database error", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception e) {
        return ResponseEntity
                .status(500)
                .body(new ErrorResponse("Internal server error", e.getMessage()));
    }
}