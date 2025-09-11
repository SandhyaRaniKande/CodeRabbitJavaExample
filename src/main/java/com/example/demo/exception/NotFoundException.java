package com.example.demo.exception;

public class NotFoundException extends RuntimeException {
    /**
 * Constructs a NotFoundException with the specified detail message.
 *
 * @param message a descriptive message explaining what was not found or why the not-found condition occurred
 */
public NotFoundException(String message) { super(message); }
}