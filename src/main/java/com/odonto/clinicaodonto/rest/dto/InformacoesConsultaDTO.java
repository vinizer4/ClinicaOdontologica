package com.odonto.clinicaodonto.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesConsultaDTO {
    private Integer codigo;
    private String cpf;
    private String nomePaciente;

    private String nomeDentista;
    private String dataConsulta;

    private String horaConsulta;
    private String status;

    private BigDecimal total;
    private List<InformacaoItemConsultaDTO> itens;
}