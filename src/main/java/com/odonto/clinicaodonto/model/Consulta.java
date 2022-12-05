package com.odonto.clinicaodonto.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Consulta(){

    };

    public Consulta(Long id, Dentista dentista, Paciente paciente, LocalDate data, LocalTime hora) {
        this.id = id;
        this.dentista = dentista;
        this.paciente = paciente;
        this.data = data;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", dentista=" + dentista +
                ", paciente=" + paciente +
                ", data=" + data +
                ", hora=" + hora +
                '}';
    }
}
