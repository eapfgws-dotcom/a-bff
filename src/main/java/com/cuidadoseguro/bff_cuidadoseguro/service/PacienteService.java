package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.PacienteDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private HttpHeaders buildHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();

        if (token != null && !token.startsWith("Bearer ")) {
            token = "Bearer " + token;
        }

        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public ResponseEntity<String> listar(String token) {

        System.out.println("BFF ENVIA: " + token);
        HttpHeaders headers = buildHeaders(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                gatewayUrl + "/pacientes",
                HttpMethod.GET,
                entity,
                String.class
        );
    }

    
    public PacienteDto obtener(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer "+token);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<PacienteDto> response = restTemplate.exchange(
                gatewayUrl + "/pacientes/" + id,
                HttpMethod.GET,
                entity,
                PacienteDto.class
        );

        return response.getBody();
    }

    public PacienteDto crear(String token, PacienteDto paciente) {
        HttpHeaders headers = buildHeaders(token);

        HttpEntity<PacienteDto> entity = new HttpEntity<>(paciente, headers);

        ResponseEntity<PacienteDto> response = restTemplate.exchange(
                gatewayUrl + "/pacientes",
                HttpMethod.POST,
                entity,
                PacienteDto.class
        );

        return response.getBody();
    }

    public PacienteDto actualizar(String token, Long id, PacienteDto paciente) {
        HttpHeaders headers = buildHeaders(token);

        HttpEntity<PacienteDto> entity = new HttpEntity<>(paciente, headers);

        ResponseEntity<PacienteDto> response = restTemplate.exchange(
                gatewayUrl + "/pacientes/" + id,
                HttpMethod.PUT,
                entity,
                PacienteDto.class
        );

        return response.getBody();
    }
    public void eliminar(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer "+token);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                gatewayUrl + "/pacientes/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }
}