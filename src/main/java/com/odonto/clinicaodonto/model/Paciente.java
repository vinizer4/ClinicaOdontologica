package com.odonto.clinicaodonto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 80)
    private String sobrenome;

    @Column(name = "cpf", nullable = false, length = 80)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false, length = 10)
    private Date data;


    //relacao

    //um endereço pra um paciente
    @OneToOne (cascade = CascadeType.ALL) //os eventos anteriores serão sempre refletidos nas Entities relacionadas.
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;


    //relação
    // um paciente para varias consultas
    @OneToMany(mappedBy = "paciente")
    List<Consulta> consultaList;


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Paciente(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }
}
