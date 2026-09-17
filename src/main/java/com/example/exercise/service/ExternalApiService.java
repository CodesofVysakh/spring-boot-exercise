package com.example.exercise.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalApiService {

    private final RestClient restClient;
    private final String baseUrl;

    public ExternalApiService(RestClient restClient,
                              @Value("${external.api.base-url}") String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }

    public String callExternal() {
        return restClient.get()
                .uri(baseUrl)
                .retrieve()
                .body(String.class);
    }
}