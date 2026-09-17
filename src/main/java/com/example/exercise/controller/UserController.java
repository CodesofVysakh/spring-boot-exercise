package com.example.exercise.controller;

import com.example.exercise.dto.UserRequest;
import com.example.exercise.dto.UserResponse;
import com.example.exercise.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST — create user
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    // GET — search users with pagination
    @GetMapping("/search")
    public Page<UserResponse> search(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.searchUsers(name, page, size);
    }

    // GET — JOIN query (users with orders above amount)
    @GetMapping("/with-orders-above")
    public List<UserResponse> withOrdersAbove(@RequestParam BigDecimal minAmount) {
        return userService.findUsersWithOrdersAbove(minAmount);
    }
}