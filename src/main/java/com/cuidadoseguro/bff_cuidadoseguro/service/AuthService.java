package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String login(LoginRequest request) {
        //System.out.println("AAA: "+gatewayUrl + "/auth/login");
        return restTemplate.postForObject(
                gatewayUrl + "/auth/login",
                request,
                String.class
        );
    }

    public String register(RegisterRequest request) {
        return restTemplate.postForObject(
                gatewayUrl + "/auth/register",
                request,
                String.class
        );
    }

    public String refresh(RefreshRequest request) {
        return restTemplate.postForObject(
                gatewayUrl + "/auth/refresh",
                request,
                String.class
        );
    }

    public String logout(LogoutRequest request) {
        return restTemplate.postForObject(
                gatewayUrl + "/auth/logout",
                request,
                String.class
        );
    }

    public String validate(String token) {

    String url = gatewayUrl + "/auth/validate";

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + token);

    HttpEntity<Void> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            String.class
    );

    return response.getBody();
}

    public String health() {
        return restTemplate.getForObject(
                gatewayUrl + "/auth/health",
                String.class
        );
    }
}