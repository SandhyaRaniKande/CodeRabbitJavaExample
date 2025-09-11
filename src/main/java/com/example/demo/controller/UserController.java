package com.example.demo.controller;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Creates a UserController wired with the provided UserService.
     *
     * <p>Constructor-based dependency injection is used to supply the controller's service.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user from the provided request and returns the created user.
     *
     * @param req request body containing the new user's username, password, and role
     * @return ResponseEntity containing the created UserDto with HTTP 200 (OK)
     */
    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody CreateUserRequest req) {
        UserDto dto = new UserDto();
        dto.setUsername(req.getUsername());
        dto.setPassword(req.getPassword());
        dto.setRole(req.getRole());
        UserDto created = userService.createUser(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Retrieves a user by its identifier.
     *
     * @param id the user identifier from the request path
     * @return a 200 OK response containing the found UserDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
