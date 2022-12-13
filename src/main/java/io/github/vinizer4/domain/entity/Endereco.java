package io.github.vinizer4.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "endereco")
public class Endereco {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "cep", length =9)
   private String cep;

   @Column(name = "logradouro")
   private String logradouro;

   @Column(name = "numero")
   private String numero;

   @Column(name = "bairro")
   private String bairro;

   @Column(name = "localidade")
   private String localidade;
   @Column(name = "uf", length = 2)
   private String uf;
}
