package io.github.vinizer4.rest.controller;

import io.github.vinizer4.exception.InvalidRegistrationException;
import io.github.vinizer4.exception.ResourceNotFoundException;
import io.github.vinizer4.rest.dto.ConsultaDto;
import io.github.vinizer4.rest.dto.ConsultaMarcacaoDto;
import io.github.vinizer4.service.impl.ConsultaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

   @Autowired
   private ConsultaServiceImpl consultaService;

   @GetMapping()
   public ResponseEntity<List<ConsultaDto>> findAllConsultas() throws ResourceNotFoundException {
      return consultaService.findAllConsultas();
   }

   @GetMapping("{rg}")
   public ResponseEntity<List<ConsultaDto>> findConsultaByRg(@PathVariable String rg) throws ResourceNotFoundException {
      return consultaService.findConsultaByRg(rg);
   }

   @PostMapping()
   @ResponseBody
   public ResponseEntity<ConsultaDto> saveConsulta(@RequestBody @Valid ConsultaMarcacaoDto consultaMarcacao) throws InvalidRegistrationException {
      return consultaService.saveConsulta(consultaMarcacao);
   }

   @PutMapping()
   @ResponseBody
   public ResponseEntity<ConsultaDto> updateConsultaByRg(@RequestBody @Valid ConsultaMarcacaoDto consultaMarcacao) throws InvalidRegistrationException, ResourceNotFoundException {
      return consultaService.updateConsultaByRg(consultaMarcacao);
   }

   @DeleteMapping()
   public ResponseEntity<String> deleteConsulta(@RequestBody @Valid ConsultaMarcacaoDto consultaMarcacao) throws ResourceNotFoundException {
      return consultaService.deleteConsulta(consultaMarcacao);
   }

}
