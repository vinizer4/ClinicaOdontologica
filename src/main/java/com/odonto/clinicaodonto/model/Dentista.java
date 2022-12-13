package com.odonto.clinicaodonto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dentista")
public class Dentista implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 80)
    private String sobrenome;

    @Column(name = "matricula", nullable = false, length = 80)
    private Integer matricula;

    //relação
    @OneToMany(mappedBy = "dentista")
    List<Consulta> consultasList;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
