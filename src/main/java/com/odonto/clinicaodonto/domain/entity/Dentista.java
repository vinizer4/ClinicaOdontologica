package com.odonto.clinicaodonto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dentista")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 80)
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 80)
    @NotEmpty(message = "Campo sobrenome é obrigatório")
    private String sobrenome;

    @Column(name = "matricula", nullable = false, length = 80)
    @NotEmpty(message = "Campo matrícula é obrigatório")
    private Integer matricula;

    //relação
    @OneToMany(mappedBy = "dentista")@JsonIgnore
    private List<Consulta> consultasList;

}
