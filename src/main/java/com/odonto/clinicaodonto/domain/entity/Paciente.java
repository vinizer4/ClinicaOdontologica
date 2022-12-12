package com.odonto.clinicaodonto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente  {

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

    @Column(name = "cpf", nullable = false, length = 80)
    @NotEmpty(message = "CPF é obrigatório")
    @CPF(message = "CPF Inválido")
    private String cpf;

    @Column(name = "data_nascimento", nullable = false, length = 10)
    @NotEmpty(message = "Data nascimento é obrigatório")
    private Date data;


    //relacao

    //um endereço pra um paciente
    @OneToOne (cascade = CascadeType.ALL) //os eventos anteriores serão sempre refletidos nas Entities relacionadas.
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;


    //relação
    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    List<Consulta> consultaList;

}
