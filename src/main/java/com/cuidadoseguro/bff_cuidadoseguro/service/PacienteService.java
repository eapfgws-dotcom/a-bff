package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.PacienteDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private HttpHeaders buildHeaders(String token) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(token);

        return headers;
    }

    public ResponseEntity<String> listar(String token) {

        System.out.println("BFF ENVIA: " + token);
        HttpHeaders headers = buildHeaders(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(
                    gatewayUrl + "/pacientes",
                    HttpMethod.GET,
                    entity,
                    String.class
            );
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    
    public PacienteDto obtener(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer "+token);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<PacienteDto> response = restTemplate.exchange(
                    gatewayUrl + "/pacientes/" + id,
                    HttpMethod.GET,
                    entity,
                    PacienteDto.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error calling gateway: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        }
    }

    public PacienteDto crear(String token, PacienteDto paciente) {
        HttpHeaders headers = buildHeaders(token);

        HttpEntity<PacienteDto> entity = new HttpEntity<>(paciente, headers);

        try {
            ResponseEntity<PacienteDto> response = restTemplate.exchange(
                    gatewayUrl + "/pacientes",
                    HttpMethod.POST,
                    entity,
                    PacienteDto.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error calling gateway: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        }
    }

    public PacienteDto actualizar(String token, Long id, PacienteDto paciente) {
        HttpHeaders headers = buildHeaders(token);

        HttpEntity<PacienteDto> entity = new HttpEntity<>(paciente, headers);

        try {
            ResponseEntity<PacienteDto> response = restTemplate.exchange(
                    gatewayUrl + "/pacientes/" + id,
                    HttpMethod.PUT,
                    entity,
                    PacienteDto.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error calling gateway: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        }
    }
    public void eliminar(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer "+token);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(
                    gatewayUrl + "/pacientes/" + id,
                    HttpMethod.DELETE,
                    entity,
                    Void.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error calling gateway: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        }
    }
}