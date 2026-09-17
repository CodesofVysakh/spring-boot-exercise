package com.example.exercise.controller;

import com.example.exercise.service.ExternalApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external")
@RequiredArgsConstructor
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    // GET — calls external API (Google)
    @GetMapping("/google")
    public String callGoogle() {
        return externalApiService.callExternal();
    }
}