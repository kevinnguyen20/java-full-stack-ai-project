package com.demo.userservice.service;

import com.demo.userservice.dto.RegisterRequest;
import com.demo.userservice.dto.UserResponse;
import com.demo.userservice.model.User;
import com.demo.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserResponse register(RegisterRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());

        // Persist the user in the database
        User savedUser = repository.save(user);

        return new UserResponse(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getPassword(),
            savedUser.getFirstName(),
            savedUser.getLastName(),
            savedUser.getCreatedAt(),
            savedUser.getUpdatedAt()
        );
    }

    public UserResponse getUserProfile(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.getFirstName(),
            user.getLastName(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
