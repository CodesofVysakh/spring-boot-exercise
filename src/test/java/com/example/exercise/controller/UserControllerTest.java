package com.example.exercise.controller;

import com.example.exercise.dto.UserResponse;
import com.example.exercise.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldReturnPaginatedUsers() throws Exception {
        Page<UserResponse> page = new PageImpl<>(
                List.of(UserResponse.builder()
                        .id(1L).name("Alice").email("alice@example.com").build()),
                PageRequest.of(0, 10), 1);

        when(userService.searchUsers(anyString(), anyInt(), anyInt())).thenReturn(page);

        mockMvc.perform(get("/api/users/search")
                        .param("name", "Ali")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Alice"))
                .andExpect(jsonPath("$.content[0].email").value("alice@example.com"));
    }
}