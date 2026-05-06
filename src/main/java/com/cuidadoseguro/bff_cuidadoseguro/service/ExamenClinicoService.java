package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.ExamenClinicoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamenClinicoService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/examenes";

    public List<ExamenClinicoDto> listarTodos() {
        ResponseEntity<ExamenClinicoDto[]> response = restTemplate.getForEntity(
                gatewayUrl + BASE_PATH,
                ExamenClinicoDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    public ExamenClinicoDto guardar(ExamenClinicoDto examen) {
        return restTemplate.postForObject(
                gatewayUrl + BASE_PATH,
                examen,
                ExamenClinicoDto.class
        );
    }

    public ExamenClinicoDto buscarPorId(Long id) {
        return restTemplate.getForObject(
                gatewayUrl + BASE_PATH + "/" + id,
                ExamenClinicoDto.class
        );
    }

    public void eliminar(Long id) {
        restTemplate.delete(
                gatewayUrl + BASE_PATH + "/" + id
        );
    }
}