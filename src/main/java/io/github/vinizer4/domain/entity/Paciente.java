package io.github.vinizer4.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "paciente")
public class Paciente {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;
   @Column(name = "nome", nullable = false, length = 50)
   private String nome;
   @Column(name = "sobrenome", nullable = false, length = 250)
   private String sobrenome;
   @Column(name = "rg", unique = true, nullable = false,length = 10)
   private String rg;
   @Column(name = "dataCadastro", nullable = false)
   private LocalDate dataCadastro;
   @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
   @JoinColumn(name = "endereco_id")
   private Endereco endereco;
}
