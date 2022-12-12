package com.odonto.clinicaodonto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consulta")

public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //relacao
    @ManyToOne //muitas consultas para um dentista
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;
    //relacao
    @ManyToOne //muitas consultas para um dentista
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDate data;
    private LocalTime hora;


}
