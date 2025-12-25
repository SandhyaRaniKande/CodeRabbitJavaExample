package com.example.demo.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

// If AssertJ is available in the project, you can switch to:
// import static org.assertj.core.api.Assertions.assertThat;

class ApiExceptionHandlerTest {

    private final ApiExceptionHandler handler = new ApiExceptionHandler();

    /**
     * Minimal NotFoundException substitute for testing if the project doesn't define it.
     * If the real NotFoundException exists in the main sources with the same package,
     * this local definition will be shadowed by the real one during compilation.
     */
    static class NotFoundException extends RuntimeException {
        NotFoundException(String message) { super(message); }
        NotFoundException() { super(); }
    }

    @Nested
    @DisplayName("handleNotFound")
    class HandleNotFound {

        @Test
        @DisplayName("returns 404 status with exception message as body (happy path)")
        void returns404WithMessage() {
            String msg = "Resource with id=123 not found";
            ResponseEntity<String> response = handler.handleNotFound(new NotFoundException(msg));

            assertEquals(404, response.getStatusCodeValue(), "Status code should be 404");
            assertEquals(msg, response.getBody(), "Body should contain the exception message");
        }

        @Test
        @DisplayName("returns 404 status with null body when exception message is null (edge case)")
        void returns404WithNullBodyWhenMessageNull() {
            ResponseEntity<String> response = handler.handleNotFound(new NotFoundException());

            assertEquals(404, response.getStatusCodeValue(), "Status code should be 404");
            assertNull(response.getBody(), "Body should be null when exception message is null");
        }
    }

    @Nested
    @DisplayName("handleOther")
    class HandleOther {

        @Test
        @DisplayName("returns 500 status with fixed 'Internal error' body for generic exceptions")
        void returns500WithFixedMessage() {
            Exception ex = new Exception("some internal failure");
            ResponseEntity<String> response = handler.handleOther(ex);

            assertEquals(500, response.getStatusCodeValue(), "Status code should be 500");
            assertEquals("Internal error", response.getBody(), "Body should be the fixed internal error message");
        }

        @Test
        @DisplayName("accepts RuntimeException subclasses and still responds with 500 and fixed message")
        void acceptsRuntimeException() {
            RuntimeException ex = new IllegalStateException("illegal state");
            ResponseEntity<String> response = handler.handleOther(ex);

            assertEquals(500, response.getStatusCodeValue(), "Status code should be 500");
            assertEquals("Internal error", response.getBody(), "Body should be the fixed internal error message");
        }
    }
}