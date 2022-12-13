package io.github.vinizer4.rest.controller;


import io.github.vinizer4.exception.ResourceNotFoundException;
import io.github.vinizer4.rest.dto.PacienteDto;
import io.github.vinizer4.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

   @Autowired
   private PacienteServiceImpl pacienteService;

   @GetMapping()
   public ResponseEntity<List<PacienteDto>> findAllPacientes(){
      return pacienteService.findAllPacientes();
   }

   @GetMapping("{rg}")
   public ResponseEntity<PacienteDto> findByRg(@PathVariable String rg){
      return pacienteService.findByRg(rg);
   }

   @PostMapping()
   @ResponseBody
   public ResponseEntity<PacienteDto> savePaciente(@RequestBody @Valid PacienteDto pacienteDto){
      System.out.println(pacienteDto);
      return pacienteService.savePaciente(pacienteDto);
   }

   @PutMapping()
   @ResponseBody
   public ResponseEntity<PacienteDto> updatePacienteByRg(@RequestBody @Valid PacienteDto pacienteDto){
      return pacienteService.updatePacienteByRg(pacienteDto);
   }

   @DeleteMapping("{rg}")
   public ResponseEntity<String> deletePaciente(@PathVariable String rg) throws ResourceNotFoundException {
      return pacienteService.deletePaciente(rg);
   }

}
