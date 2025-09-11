/*
  Tests for UserDto.
  Detected testing library/framework: JUnit (prefer JUnit 5 Jupiter if available).
  Purpose: Validate getters/setters, default state, and edge inputs for this pure DTO.
*/
package com.example.demo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    @DisplayName("Default constructor initializes all fields to null")
    void defaultConstructorInitializesNulls() {
        UserDto dto = new UserDto();
        assertAll(
            () -> assertNull(dto.getId(), "id should default to null"),
            () -> assertNull(dto.getUsername(), "username should default to null"),
            () -> assertNull(dto.getPassword(), "password should default to null"),
            () -> assertNull(dto.getRole(), "role should default to null")
        );
    }

    @Test
    @DisplayName("Setters assign values and getters return them (happy path)")
    void settersAndGettersHappyPath() {
        UserDto dto = new UserDto();
        Long id = 123L;
        String username = "alice";
        String password = "s3cr3t";
        String role = "ADMIN";

        dto.setId(id);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setRole(role);

        assertAll(
            () -> assertEquals(id, dto.getId()),
            () -> assertEquals(username, dto.getUsername()),
            () -> assertEquals(password, dto.getPassword()),
            () -> assertEquals(role, dto.getRole())
        );
    }

    @Test
    @DisplayName("Setters accept null values and getters return null")
    void settersAcceptNulls() {
        UserDto dto = new UserDto();

        dto.setId(null);
        dto.setUsername(null);
        dto.setPassword(null);
        dto.setRole(null);

        assertAll(
            () -> assertNull(dto.getId()),
            () -> assertNull(dto.getUsername()),
            () -> assertNull(dto.getPassword()),
            () -> assertNull(dto.getRole())
        );
    }

    @Test
    @DisplayName("Setters handle empty strings without modification")
    void settersHandleEmptyStrings() {
        UserDto dto = new UserDto();

        dto.setUsername("");
        dto.setPassword("");
        dto.setRole("");

        assertAll(
            () -> assertEquals("", dto.getUsername()),
            () -> assertEquals("", dto.getPassword()),
            () -> assertEquals("", dto.getRole())
        );
    }

    @Test
    @DisplayName("Large and boundary-like values are stored and returned intact")
    void boundaryValues() {
        UserDto dto = new UserDto();

        Long largeId = Long.MAX_VALUE;
        String longUsername = "u".repeat(1024);
        String longPassword = "p".repeat(2048);
        String role = "USER";

        dto.setId(largeId);
        dto.setUsername(longUsername);
        dto.setPassword(longPassword);
        dto.setRole(role);

        assertAll(
            () -> assertEquals(Long.MAX_VALUE, dto.getId()),
            () -> assertEquals(1024, dto.getUsername().length()),
            () -> assertEquals(2048, dto.getPassword().length()),
            () -> assertEquals("USER", dto.getRole())
        );
    }

    @Test
    @DisplayName("Instances are independent; modifying one does not affect another")
    void instancesAreIndependent() {
        UserDto a = new UserDto();
        UserDto b = new UserDto();

        a.setUsername("alice");
        b.setUsername("bob");

        assertAll(
            () -> assertEquals("alice", a.getUsername()),
            () -> assertEquals("bob", b.getUsername())
        );
    }

    @Test
    @DisplayName("Two distinct instances with identical field values are not the same reference")
    void equalityIsReferenceByDefault() {
        UserDto a = new UserDto();
        a.setId(1L);
        a.setUsername("user");
        a.setPassword("pw");
        a.setRole("ADMIN");

        UserDto b = new UserDto();
        b.setId(1L);
        b.setUsername("user");
        b.setPassword("pw");
        b.setRole("ADMIN");

        assertNotSame(a, b);
        assertFalse(a == b, "Different instances should not be the same reference");
        // Note: No equals/hashCode overrides in DTO; we avoid asserting equals() semantics.
    }

    @Test
    @DisplayName("Fields can be updated after initial set (mutability check)")
    void fieldsAreMutable() {
        UserDto dto = new UserDto();

        dto.setUsername("initial");
        assertEquals("initial", dto.getUsername());

        dto.setUsername("updated");
        assertEquals("updated", dto.getUsername(), "username should reflect latest value");
    }
}