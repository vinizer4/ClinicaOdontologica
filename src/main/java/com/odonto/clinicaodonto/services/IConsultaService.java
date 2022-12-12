package com.odonto.clinicaodonto.services;

import com.odonto.clinicaodonto.dto.ConsultaDTO;
import com.odonto.clinicaodonto.model.Consulta;

import java.util.Optional;

public interface IConsultaService {
    Consulta salvar(ConsultaDTO dto);
}
