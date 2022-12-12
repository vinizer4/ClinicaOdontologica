package io.github.vinizer4.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaMarcacaoDto {
   private int consultaId;
   @NotNull(message = "A Data da consulta é obrigatória.")
   private LocalDateTime dhConsulta;
   @NotBlank(message = "RG do paciente é obrigatório.")
   private String rgPaciente;
   @NotBlank(message="Matricula do dentista é obrigatório.")
   private String  matriculaDentista;
}