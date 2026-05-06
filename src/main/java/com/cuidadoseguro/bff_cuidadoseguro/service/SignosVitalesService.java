package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.SignosVitalesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SignosVitalesService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/signos-vitales";

    public List<SignosVitalesDto> listarTodos() {
        ResponseEntity<SignosVitalesDto[]> response = restTemplate.getForEntity(
                gatewayUrl + BASE_PATH,
                SignosVitalesDto[].class
        );

        return response.getBody() != null
                ? Arrays.asList(response.getBody())
                : List.of();
    }

    public SignosVitalesDto guardar(SignosVitalesDto SignosVitalesDto) {
        return restTemplate.postForObject(
                gatewayUrl + BASE_PATH,
                SignosVitalesDto,
                SignosVitalesDto.class
        );
    }

    public SignosVitalesDto buscarPorId(Long id) {
        return restTemplate.getForObject(
                gatewayUrl + BASE_PATH + "/" + id,
                SignosVitalesDto.class
        );
    }

    public void eliminar(Long id) {
        restTemplate.delete(
                gatewayUrl + BASE_PATH + "/" + id
        );
    }
}