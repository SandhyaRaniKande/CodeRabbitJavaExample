package com.example.demo.repository;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
 * Find a User by username.
 *
 * Derived query method implemented by Spring Data JPA; returns an Optional containing the matching User,
 * or an empty Optional if no user with the given username exists.
 *
 * @param username the username to look up
 * @return an Optional containing the User if found, otherwise empty
 */
Optional<User> findByUsername(String username);
}
