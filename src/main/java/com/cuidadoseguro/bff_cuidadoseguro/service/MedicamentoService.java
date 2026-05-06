package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.MedicamentoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/medicamentos";

    public List<MedicamentoDto> listar() {
        ResponseEntity<MedicamentoDto[]> response = restTemplate.getForEntity(
                gatewayUrl + BASE_PATH,
                MedicamentoDto[].class
        );

        return response.getBody() != null
                ? Arrays.asList(response.getBody())
                : List.of();
    }

    public MedicamentoDto guardar(MedicamentoDto MedicamentoDto) {
        return restTemplate.postForObject(
                gatewayUrl + BASE_PATH,
                MedicamentoDto,
                MedicamentoDto.class
        );
    }
}