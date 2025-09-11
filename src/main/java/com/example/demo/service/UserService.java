package com.example.demo.service;

import com.example.demo.dto.UserDto;

public interface UserService {
    /**
 * Create a new user from the provided DTO.
 *
 * The provided UserDto contains the user details to persist. Implementations typically
 * return a representation of the created user that may include generated identifiers
 * or any fields populated by the persistence layer.
 *
 * @param user DTO containing the user details to create
 * @return the created UserDto, usually including any generated id or defaulted fields
 */
UserDto createUser(UserDto user);
    /**
 * Retrieve a user by its identifier.
 *
 * @param id the user's unique identifier
 * @return the corresponding UserDto, or null if no user exists with the given id
 */
UserDto findById(Long id);
}