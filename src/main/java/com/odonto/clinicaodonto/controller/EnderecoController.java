package com.odonto.clinicaodonto.controller;

import com.odonto.clinicaodonto.model.Endereco;
import com.odonto.clinicaodonto.services.EnderecoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoServices service;
    /// private PersonServices service = new PersonServices();

    @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<Endereco> findById() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Endereco findById(
            @PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Endereco create(
            @RequestBody Endereco endereco) {
        return service.create(endereco);
    }

    @PutMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Endereco update(
            @RequestBody Endereco endereco) {
        return service.update(endereco);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
