package io.github.vinizer4.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "consulta")
public class Consulta {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(nullable = false)
   private int consultaId;
   @OneToOne
   @JoinColumn(name = "paciente_id")
   private Paciente paciente;
   @OneToOne
   @JoinColumn(name = "dentista_id")
   private Dentista dentista;

   @Column(name = "data_hora_consulta")
   private LocalDateTime dhConsulta;

}
