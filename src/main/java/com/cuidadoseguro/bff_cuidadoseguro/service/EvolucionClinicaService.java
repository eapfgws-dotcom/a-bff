package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.EvolucionClinicaDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvolucionClinicaService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private HttpHeaders headers(String token) {
        HttpHeaders headers = new HttpHeaders();

        // 🔴 IMPORTANTE: el token debe venir con "Bearer ..."
        headers.set("Authorization", token);

        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public List<EvolucionClinicaDto> listarTodos(String token) {
        HttpEntity<Void> entity = new HttpEntity<>(headers(token));

        ResponseEntity<EvolucionClinicaDto[]> response = restTemplate.exchange(
                gatewayUrl + "/evoluciones",
                HttpMethod.GET,
                entity,
                EvolucionClinicaDto[].class
        );

        return Arrays.asList(response.getBody());
    }

    public EvolucionClinicaDto guardar(String token, EvolucionClinicaDto evolucion) {
        HttpEntity<EvolucionClinicaDto> entity =
                new HttpEntity<>(evolucion, headers(token));

        ResponseEntity<EvolucionClinicaDto> response = restTemplate.exchange(
                gatewayUrl + "/evoluciones",
                HttpMethod.POST,
                entity,
                EvolucionClinicaDto.class
        );

        return response.getBody();
    }

    public EvolucionClinicaDto buscarPorId(String token, Long id) {
        HttpEntity<Void> entity = new HttpEntity<>(headers(token));

        ResponseEntity<EvolucionClinicaDto> response = restTemplate.exchange(
                gatewayUrl + "/evoluciones/" + id,
                HttpMethod.GET,
                entity,
                EvolucionClinicaDto.class
        );

        return response.getBody();
    }

    public void eliminar(String token, Long id) {
        HttpEntity<Void> entity = new HttpEntity<>(headers(token));

        restTemplate.exchange(
                gatewayUrl + "/evoluciones/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }
}