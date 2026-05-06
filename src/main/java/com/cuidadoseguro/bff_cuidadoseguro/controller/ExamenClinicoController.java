package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.ExamenClinicoDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.ExamenClinicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/examenes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExamenClinicoController {

    private final ExamenClinicoService service;

    @GetMapping
    public List<ExamenClinicoDto> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public ExamenClinicoDto guardar(@RequestBody ExamenClinicoDto examen) {
        return service.guardar(examen);
    }

    @GetMapping("/{id}")
    public ExamenClinicoDto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}