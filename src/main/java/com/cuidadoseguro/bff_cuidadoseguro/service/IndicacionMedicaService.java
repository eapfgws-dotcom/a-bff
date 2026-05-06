package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.IndicacionMedicaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndicacionMedicaService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/indicaciones";

    public List<IndicacionMedicaDto> listarTodos() {
        ResponseEntity<IndicacionMedicaDto[]> response = restTemplate.getForEntity(
                gatewayUrl + BASE_PATH,
                IndicacionMedicaDto[].class
        );

        return response.getBody() != null
                ? Arrays.asList(response.getBody())
                : List.of();
    }

    public IndicacionMedicaDto guardar(IndicacionMedicaDto indicacion) {
        return restTemplate.postForObject(
                gatewayUrl + BASE_PATH,
                indicacion,
                IndicacionMedicaDto.class
        );
    }

    public IndicacionMedicaDto buscarPorId(Long id) {
        return restTemplate.getForObject(
                gatewayUrl + BASE_PATH + "/" + id,
                IndicacionMedicaDto.class
        );
    }

    public void eliminar(Long id) {
        restTemplate.delete(
                gatewayUrl + BASE_PATH + "/" + id
        );
    }
}