package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.PacienteDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
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
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public List<PacienteDto> listar(String token) {
        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders(token));

        ResponseEntity<PacienteDto[]> response = restTemplate.exchange(
                gatewayUrl + "/pacientes",
                HttpMethod.GET,
                entity,
                PacienteDto[].class
        );

        return Arrays.asList(response.getBody());
    }

    public PacienteDto obtener(String token, Long id) {
        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders(token));

        ResponseEntity<PacienteDto> response = restTemplate.exchange(
                gatewayUrl + "/pacientes/" + id,
                HttpMethod.GET,
                entity,
                PacienteDto.class
        );

        return response.getBody();
    }

    public PacienteDto crear(String token, PacienteDto paciente) {
        HttpEntity<PacienteDto> entity = new HttpEntity<>(paciente, buildHeaders(token));

        ResponseEntity<PacienteDto> response = restTemplate.exchange(
                gatewayUrl + "/pacientes",
                HttpMethod.POST,
                entity,
                PacienteDto.class
        );

        return response.getBody();
    }

    public PacienteDto actualizar(String token, Long id, PacienteDto paciente) {
        HttpEntity<PacienteDto> entity = new HttpEntity<>(paciente, buildHeaders(token));

        ResponseEntity<PacienteDto> response = restTemplate.exchange(
                gatewayUrl + "/pacientes/" + id,
                HttpMethod.PUT,
                entity,
                PacienteDto.class
        );

        return response.getBody();
    }

    public void eliminar(String token, Long id) {
        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders(token));

        restTemplate.exchange(
                gatewayUrl + "/pacientes/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }
}