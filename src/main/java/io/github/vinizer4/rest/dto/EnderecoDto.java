package io.github.vinizer4.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDto {
   @Size(min=9, max=9, message = "O CEP precisa ter 9 caracteres.")
   private String cep;
   private String logradouro;
   private String numero;
   private String bairro;
   private String localidade;
   @Size(min=2, max=2, message = "O UF precisa ter 2 caracteres.")
   private String uf;
}
