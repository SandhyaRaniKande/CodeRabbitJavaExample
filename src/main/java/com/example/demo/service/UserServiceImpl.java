package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a UserServiceImpl with the given repository dependency.
     *
     * The repository is injected (constructor injection) following the Dependency
     * Inversion Principle so the implementation depends on the repository interface.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository; // DIP: depends on interface
    }

    /**
     * Creates a new user from the provided DTO, persists it, and returns the saved user as a DTO.
     *
     * The role defaults to "ROLE_USER" when the incoming DTO's role is null.
     *
     * @param userDto DTO containing username, password, and an optional role for the new user
     * @return a UserDto for the persisted user (includes generated id)
     */
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
// in real app hash the password
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole() == null ? "ROLE_USER" : userDto.getRole());

        User saved = userRepository.save(user);
        return toDto(saved);
    }

    /**
     * Retrieves a user by its ID and returns a mapped UserDto.
     *
     * @param id the database identifier of the user to retrieve
     * @return a UserDto representing the found user (password not included)
     * @throws NotFoundException if no user with the given id exists
     */
    @Override
    public UserDto findById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return toDto(u);
    }

    /**
     * Convert a User entity to a UserDto.
     *
     * Maps the user's id, username, and role into a new DTO. The user's password and other
     * sensitive/internal fields are intentionally not copied.
     *
     * @param user the source User entity
     * @return a UserDto containing id, username, and role from the given user
     */
    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
}
