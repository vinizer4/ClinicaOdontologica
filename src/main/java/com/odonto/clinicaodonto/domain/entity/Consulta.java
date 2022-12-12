package com.odonto.clinicaodonto.domain.entity;

import com.odonto.clinicaodonto.domain.enums.StatusConsulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //relacao
    @ManyToOne //muitas consultas para um dentista
    @JoinColumn(name = "dentista_id")
    @NotEmpty(message = "Dentista é obrigatório")
    private Dentista dentista;
    //relacao
    @ManyToOne //muitas consultas para um paciente
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column(name = "data")
    @NotEmpty(message = "Data é obrigatório")
    private LocalDate dataConsulta;

    @Column(name = "hora")
    @NotEmpty(message = "Hora é obrigatório")
    private LocalTime horaConsulta;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusConsulta status;


    @OneToMany(mappedBy = "consulta")
    private List<ItemConsulta> itens;
}
