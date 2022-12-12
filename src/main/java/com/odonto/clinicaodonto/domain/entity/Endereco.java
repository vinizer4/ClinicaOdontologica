package com.odonto.clinicaodonto.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rua", nullable = false, length = 80)
    @NotEmpty(message = "Rua é obrigatório")
    private String rua;

    @Column(name = "numero", nullable = false, length = 80)
    @NotEmpty(message = "Número é obrigatório")
    private String numero;

    @Column(name = "bairro", nullable = false, length = 80)
    @NotEmpty(message = "Bairro é obrigatório")
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 10)
    @NotEmpty(message = "Cidade é obrigatório")
    private Date cidade;

}