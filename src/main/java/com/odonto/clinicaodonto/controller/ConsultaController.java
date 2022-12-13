package com.odonto.clinicaodonto.controller;


import com.odonto.clinicaodonto.dto.ConsultaDTO;
import com.odonto.clinicaodonto.model.Consulta;
import com.odonto.clinicaodonto.services.impl.ConsultaServices;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

    private ConsultaServices service;

    public ConsultaController( ConsultaServices service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Long save(@RequestBody ConsultaDTO dto){
        Consulta consulta = service.salvar(dto);
        return consulta.getId();
    }

}
