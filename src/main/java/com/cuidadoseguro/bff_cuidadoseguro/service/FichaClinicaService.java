package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.FichaClinicaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FichaClinicaService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/fichas";

    public List<FichaClinicaDto> listar() {
        ResponseEntity<FichaClinicaDto[]> response = restTemplate.getForEntity(
                gatewayUrl + BASE_PATH,
                FichaClinicaDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    public FichaClinicaDto guardar(FichaClinicaDto ficha) {
        return restTemplate.postForObject(
                gatewayUrl + BASE_PATH,
                ficha,
                FichaClinicaDto.class
        );
    }
}