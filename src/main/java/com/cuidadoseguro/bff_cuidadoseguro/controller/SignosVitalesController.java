package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.SignosVitalesDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.SignosVitalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/signos-vitales")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SignosVitalesController {

    private final SignosVitalesService service;

    @GetMapping
    public List<SignosVitalesDto> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public SignosVitalesDto guardar(@RequestBody SignosVitalesDto SignosVitalesDto) {
        return service.guardar(SignosVitalesDto);
    }

    @GetMapping("/{id}")
    public SignosVitalesDto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}