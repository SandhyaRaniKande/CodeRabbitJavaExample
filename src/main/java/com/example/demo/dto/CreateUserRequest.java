package com.example.demo.dto;


public class CreateUserRequest {

    private String username;

    private String password;
    private String role;
/**
     * Returns the username supplied for this create-user request.
     *
     * @return the username, or {@code null} if not set
     */

    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this request.
     *
     * Passing {@code null} clears the stored username.
     *
     * @param username the username to set, or {@code null} to clear
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password supplied in this request.
     *
     * Note: this is the raw password as provided; callers must handle it securely.
     *
     * @return the password string, or {@code null} if not set
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password value.
     *
     * <p>Stores the provided password string directly on the DTO; no validation,
     * hashing, or other processing is performed by this method.</p>
     *
     * @param password the plaintext password to assign
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's role.
     *
     * @return the role assigned to the user, or {@code null} if not set
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the role identifier or name (for example, "USER" or "ADMIN")
     */
    public void setRole(String role) {
        this.role = role;
    }
}
