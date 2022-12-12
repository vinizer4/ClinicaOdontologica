package io.github.vinizer4.domain.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "dentista")
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Column(name = "sobrenome", nullable = false, length = 250)
    private String sobrenome;
    @Column(name = "matricula", nullable = false, unique = true, length = 7)
    private String matricula;


}
