package com.example.exercise.service;

import com.example.exercise.dto.UserRequest;
import com.example.exercise.dto.UserResponse;
import com.example.exercise.entity.User;
import com.example.exercise.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldReturnResponse() {
        User saved = User.builder()
                .id(1L).name("Alice").email("alice@example.com")
                .build();
        when(userRepository.save(any(User.class))).thenReturn(saved);

        UserResponse response = userService.createUser(
                UserRequest.builder().name("Alice").email("alice@example.com").build());

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Alice", response.getName());
        assertEquals("alice@example.com", response.getEmail());
    }
}