package com.example.exercise.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ExternalApiServiceTest {

    @Test
    @SuppressWarnings({"unchecked", "rawtypes"})
    void callExternal_shouldReturnBody() {
        // Mock chain: restClient.get().uri(url).retrieve().body(String.class)
        RestClient restClient = mock(RestClient.class);
        RestClient.RequestHeadersUriSpec uriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec headerSpec = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);

        when(restClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headerSpec);
        when(headerSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(String.class)).thenReturn("<html>google</html>");

        ExternalApiService service = new ExternalApiService(restClient, "https://www.google.com");
        String result = service.callExternal();

        assertEquals("<html>google</html>", result);
    }
}