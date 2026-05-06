package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.FichaClinicaDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.FichaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/fichas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FichaClinicaController {

    private final FichaClinicaService service;

    @GetMapping
    public List<FichaClinicaDto> listar() {
        return service.listar();
    }

    @PostMapping
    public FichaClinicaDto guardar(@RequestBody FichaClinicaDto ficha) {
        return service.guardar(ficha);
    }
}