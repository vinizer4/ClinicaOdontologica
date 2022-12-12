package com.odonto.clinicaodonto.rest.controller;

import com.odonto.clinicaodonto.domain.entity.Dentista;
import com.odonto.clinicaodonto.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    private DentistaService service;

    @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<Dentista> findById() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Dentista findById(
            @PathVariable(value = "id") Long id) {
        return service.findById(Math.toIntExact(id));
    }

    @PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Dentista create(
            @RequestBody Dentista dentista) {
        return service.create(dentista);
    }

    @PutMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Dentista update(
            @RequestBody Dentista dentista) {
        return service.update(dentista);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}