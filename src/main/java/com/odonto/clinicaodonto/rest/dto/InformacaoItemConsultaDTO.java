package com.odonto.clinicaodonto.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemConsultaDTO {
    private String nomePaciente;

    private String nomeDentista;
    private BigDecimal total;
}