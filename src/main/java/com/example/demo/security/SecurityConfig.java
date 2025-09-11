package com.example.demo.security;

import com.example.demo.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    /**
     * Create a SecurityConfig that wires in the JWT request filter used by the security filter chain.
     *
     * The injected JwtRequestFilter will be used to validate JWTs for protected requests. 
     */
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Provides a UserDetailsService that creates an in-memory UserDetails for any requested username.
     *
     * The returned service builds a User with the requested username, a BCrypt-encoded literal password
     * `"password"`, and the role `USER`. This is intended for simple authentication/testing scenarios.
     *
     * @return a UserDetailsService that supplies a UserDetails instance for a given username
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
    }

    /**
     * Exposes a PasswordEncoder bean that encodes and verifies passwords using BCrypt.
     *
     * @return a BCryptPasswordEncoder instance for hashing and verifying passwords
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates and configures a DaoAuthenticationProvider bean.
     *
     * <p>The returned provider is wired with the class's UserDetailsService and PasswordEncoder
     * to perform DAO-based authentication (loading users and verifying passwords).</p>
     *
     * @return a configured DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Exposes the application's AuthenticationManager from the provided configuration.
     *
     * Retrieves and returns the AuthenticationManager managed by the given AuthenticationConfiguration.
     *
     * @return the configured AuthenticationManager
     * @throws Exception if the AuthenticationManager cannot be obtained from the configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configures HTTP security and returns the assembled SecurityFilterChain.
     *
     * Disables CSRF protection, permits unauthenticated access to endpoints under
     * "/api/auth/**", requires authentication for all other requests, and inserts
     * the JWT request filter before the UsernamePasswordAuthenticationFilter.
     *
     * @return the configured SecurityFilterChain
     * @throws Exception if building the SecurityFilterChain from the provided HttpSecurity fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
