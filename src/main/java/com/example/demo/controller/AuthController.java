package com.example.demo.controller;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Authenticates a user and returns a JWT on successful authentication.
     *
     * Expects a JSON request body bound to a Map with keys "username" and "password".
     * If authentication succeeds, a token is generated for the provided username and returned
     * as a single-entry map: {"token": "<jwt>"}. If authentication fails, a RuntimeException
     * with message "Invalid credentials" is thrown.
     *
     * @param request a map containing "username" and "password" entries from the request body
     * @return a map with a single entry "token" mapped to the generated JWT
     * @throws RuntimeException if authentication fails (message: "Invalid credentials")
     */
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(request.get("username"));
        return Map.of("token", token);
    }
}

