package com.odonto.clinicaodonto.rest.controller;

import com.odonto.clinicaodonto.domain.entity.Paciente;
import com.odonto.clinicaodonto.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;
    /// private PersonServices service = new PersonServices();

    @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<Paciente> findById() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Paciente findById(
            @PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Paciente create(
            @RequestBody Paciente paciente) {
        return service.create(paciente);
    }

    @PutMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Paciente update(
            @RequestBody Paciente paciente) {
        return service.update(paciente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
