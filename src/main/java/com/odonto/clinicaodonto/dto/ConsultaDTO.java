package com.odonto.clinicaodonto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {

    private Long dentista;
    private Long paciente;
    private LocalDate data;
    private LocalTime hora;
}
