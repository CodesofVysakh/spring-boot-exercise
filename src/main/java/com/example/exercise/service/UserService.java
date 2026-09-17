package com.example.exercise.service;

import com.example.exercise.dto.UserRequest;
import com.example.exercise.dto.UserResponse;
import com.example.exercise.entity.User;
import com.example.exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ---- CREATE ----
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    // ---- SEARCH with pagination ----
    public Page<UserResponse> searchUsers(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return userRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(this::toResponse);
    }

    // ---- JOIN query ----
    public List<UserResponse> findUsersWithOrdersAbove(BigDecimal minAmount) {
        return userRepository.findUsersWithOrdersAbove(minAmount)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ---- helper ----
    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}