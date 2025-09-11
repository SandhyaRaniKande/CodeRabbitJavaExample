package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Handles NotFoundException by returning an HTTP 404 response with the exception message as the body.
     *
     * @param ex the NotFoundException that triggered this handler; its message is used as the response body
     * @return a ResponseEntity with status 404 (Not Found) and a body containing the exception message
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    /**
     * Handles any uncaught exceptions and converts them into a generic HTTP 500 response.
     *
     * @param ex the exception that was not handled by a more specific handler
     * @return a ResponseEntity with HTTP status 500 and body "Internal error"
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOther(Exception ex) {
        return ResponseEntity.status(500).body("Internal error");
    }
}
