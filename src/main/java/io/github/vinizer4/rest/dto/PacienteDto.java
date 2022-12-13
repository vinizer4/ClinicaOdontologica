package io.github.vinizer4.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    @NotBlank(message = "Nome do paciente é obrigatório.")
    @Size(min=2, max=50, message = "O nome precisa ser maior que 2 e nenor que 50 caracteres.")
    private String nome;
    @NotBlank(message = "Sobrenome do paciente é obrigatório.")
    @Size(min=2, max=250, message = "O nome precisa ser maior que 2 e nenor que 250 caracteres.")
    private String sobrenome;
    @NotBlank(message = "RG do paciente é obrigatório.")
    @Size(min=9, max=10, message = "Rg precisa ter 10 caracteres.")
    private String rg;
    @JsonFormat(pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataCadastro;
    private EnderecoDto endereco;

}
