package com.demo.userservice.controller;

import com.demo.userservice.dto.RegisterRequest;
import com.demo.userservice.dto.UserResponse;
import com.demo.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid@RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }
}
