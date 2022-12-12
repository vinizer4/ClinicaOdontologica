package com.odonto.clinicaodonto.service;

import com.odonto.clinicaodonto.domain.entity.Consulta;
import com.odonto.clinicaodonto.domain.enums.StatusConsulta;
import com.odonto.clinicaodonto.rest.dto.ConsultaDTO;

import java.util.Optional;


public interface ConsultaService {

    Consulta salvar( ConsultaDTO dto );

    Optional<Consulta> obterConsultaCompleta(Integer id);

    void atualizaStatus(Integer id, StatusConsulta statusConsulta);
}
