package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "mySecretKey12345"; /**
     * Generates a JWT for the given username.
     *
     * The token's subject is set to the provided username, issuedAt is the current time,
     * and the token expires one hour after issuance. The JWT is signed using HS512 with the
     * component's signing key.
     *
     * @param username the username to set as the JWT subject
     * @return a compact JWT string
     */

    public String generateToken(String username) {
        // 1 hour
        long EXPIRATION_TIME = 1000 * 60 * 60;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * Extracts the JWT subject (typically the username) from the provided token.
     *
     * @param token the JWT string to parse
     * @return the subject claim from the token, or null if the claim is absent
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Validates that the given JWT belongs to the specified username and is not expired.
     *
     * @param token the JWT string to validate
     * @param username the expected subject (username) contained in the token
     * @return true if the token's subject equals the provided username and the token is not expired; false otherwise
     */
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /**
     * Checks whether the given JWT is expired.
     *
     * @param token the JWT string to inspect
     * @return true if the token's expiration time is before the current time, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * Parse the provided JWT and return its claims.
     *
     * Uses this instance's signing key to validate and parse the token.
     *
     * @param token the compact JWT string to parse
     * @return the token's Claims (the parsed JWT body)
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
