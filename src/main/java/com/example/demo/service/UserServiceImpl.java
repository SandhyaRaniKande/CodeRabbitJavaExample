package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserProfile;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;

    public UserServiceImpl(UserRepository userRepository,UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository; // DIP: depends on interface
        this.profileRepository = userProfileRepository;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
// in real app hash the password
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole() == null ? "ROLE_USER" : userDto.getRole());

        User saved = userRepository.save(user);
        return (saved);
    }

    @Override
    public UserDto findById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return toDto(u);
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
    @Transactional
    public User createUserWithProfile(User user, UserProfile profile) {
        User savedUser = userRepository.save(user);
        profile.setUser(savedUser);
        profileRepository.save(profile);
        return savedUser;
    }
    // Update user role and profile together
    @Transactional
    public void updateUserAndProfile(Long userId, String newRole, String newAddress) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(newRole);

        UserProfile profile = user.getProfile();
        if (profile != null) {
            profile.setAddress(newAddress);
        }

        // both updates are part of the same transaction
        userRepository.save(user);
        profileRepository.save(profile);
    }

    // Delete both User + Profile in a transaction
    @Transactional
    public void deleteUserWithProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        profileRepository.delete(user.getProfile());
        userRepository.delete(user);
    }
}
