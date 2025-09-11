package com.example.demo.dto;

public class UserDto {
    private Long id;
    private String username;
    private String password; // only in requests
    private String role;
/**
     * Returns the user's identifier.
     *
     * @return the user's id, or {@code null} if not set
     */

    public Long getId() {
        return id;
    }

    /**
     * Sets the user's identifier.
     *
     * @param id the identifier to set; may be null
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the username.
     *
     * @return the username associated with this DTO, or {@code null} if not set
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password associated with this user DTO.
     *
     * <p>Intended for use in incoming requests only (not for exposing in responses).</p>
     *
     * @return the password value, or {@code null} if not set
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password value on this DTO.
     *
     * This field is intended for incoming request payloads only (not for returned responses or persistence).
     *
     * @param password the plaintext password provided in the request
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's role.
     *
     * @return the role associated with this user, or {@code null} if none is set
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the role to assign to the user
     */
    public void setRole(String role) {
        this.role = role;
    }
}
