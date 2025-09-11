package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // hashed

    @Column(nullable = false)
    private String role; // ROLE_USER, ROLE_ADMIN

/**
     * Returns the primary key identifier of this user.
     *
     * @return the database-generated id for this user, or null if not yet persisted
     */

    public Long getId() {
        return id;
    }

    /**
     * Sets the entity primary key identifier.
     *
     * <p>Assigns the JPA primary key value for this User. The `id` field is mapped as the
     * primary key with identity generation; in normal application flow it is managed by
     * the persistence provider and should not be manually changed except for testing or
     * specific use cases that require explicit id assignment.</p>
     *
     * @param id the primary key value to assign (may be null for transient/new entities)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the user's username.
     *
     * The value corresponds to the `username` column (annotated as non-null and unique);
     * for persisted entities this value will be non-null.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username (login identifier).
     *
     * The value is persisted to the `username` column and must be non-null and unique
     * according to the entity's column constraints.
     *
     * @param username the login name to assign to the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the stored (hashed) password for this user.
     *
     * @return the user's hashed password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * The value should be a hashed password suitable for storage (do not pass plaintext).
     *
     * @param password the hashed password to store
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's role.
     *
     * The role is stored as a string (for example, "ROLE_USER" or "ROLE_ADMIN").
     *
     * @return the role assigned to this user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * The role is a non-null string representing the user's authorities (for example
     * "ROLE_USER" or "ROLE_ADMIN") and is persisted to the "role" column of the users table.
     *
     * @param role the role to assign to the user (expected non-null, e.g. "ROLE_USER")
     */
    public void setRole(String role) {
        this.role = role;
    }
}