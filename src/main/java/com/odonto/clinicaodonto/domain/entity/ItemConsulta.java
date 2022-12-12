package com.odonto.clinicaodonto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_consulta")
public class ItemConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "consulta_id")@JsonIgnore
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column
    private BigDecimal total;

}
