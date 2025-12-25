package com.example.demo.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    @DisplayName("Constructor stores provided message")
    void shouldStoreProvidedMessage() {
        NotFoundException ex = new NotFoundException("Resource abc not found");
        assertEquals("Resource abc not found", ex.getMessage());
    }

    @Test
    @DisplayName("Null message is supported and preserved")
    void shouldSupportNullMessage() {
        NotFoundException ex = new NotFoundException(null);
        assertNull(ex.getMessage());
    }

    @Test
    @DisplayName("Empty message is supported and preserved")
    void shouldSupportEmptyMessage() {
        NotFoundException ex = new NotFoundException("");
        assertEquals("", ex.getMessage());
    }

    @Test
    @DisplayName("toString includes class name and message when present")
    void toStringIncludesClassNameAndMessageWhenMessagePresent() {
        NotFoundException ex = new NotFoundException("boom");
        String s = ex.toString();
        assertTrue(s.contains("NotFoundException"), "toString should contain class name");
        assertTrue(s.contains("boom"), "toString should include the message when present");
    }

    @Test
    @DisplayName("toString omits 'null' literal when message is null")
    void toStringOmitsNullWhenMessageIsNull() {
        NotFoundException ex = new NotFoundException(null);
        String s = ex.toString();
        assertTrue(s.contains("NotFoundException"), "toString should contain class name");
        assertFalse(s.contains("null"), "toString should not include the 'null' literal");
    }

    @Test
    @DisplayName("NotFoundException is a RuntimeException")
    void isInstanceOfRuntimeException() {
        assertTrue(new NotFoundException("x") instanceof RuntimeException);
    }

    @Test
    @DisplayName("Throwing NotFoundException is catchable as NotFoundException")
    void thrownAndCaughtAsNotFoundException() {
        NotFoundException ex = assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException("boom");
        });
        assertEquals("boom", ex.getMessage());
    }

    @Test
    @DisplayName("Throwing NotFoundException is catchable as RuntimeException")
    void thrownAndCaughtAsRuntimeException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            throw new NotFoundException("boom");
        });
        assertEquals("boom", ex.getMessage());
    }
}