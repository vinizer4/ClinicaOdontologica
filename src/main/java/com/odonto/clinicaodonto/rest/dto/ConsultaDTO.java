package com.odonto.clinicaodonto.rest.dto;

import com.odonto.clinicaodonto.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {

    @NotNull(message = "Dentista é obridatório")
    private Integer dentista;

    @NotNull(message = "Paciente é obridatório")
    private Integer paciente;

    @NotEmpty(message = "Data é obrigatório")
    private LocalDate data;

    @NotEmpty(message = "Hora é obrigatório")
    private LocalTime hora;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemConsultaDTO> items;

}