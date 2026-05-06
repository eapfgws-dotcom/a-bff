package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.IndicacionMedicaDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.IndicacionMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/indicaciones")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IndicacionMedicaController {

    private final IndicacionMedicaService service;

    @GetMapping
    public List<IndicacionMedicaDto> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public IndicacionMedicaDto guardar(@RequestBody IndicacionMedicaDto indicacion) {
        return service.guardar(indicacion);
    }

    @GetMapping("/{id}")
    public IndicacionMedicaDto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}