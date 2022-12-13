package io.github.vinizer4.service;

import io.github.vinizer4.rest.dto.PacienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PacienteService {

   ResponseEntity<List<PacienteDto>> findAllPacientes();
   ResponseEntity<PacienteDto> findByRg(String rg);
   ResponseEntity<PacienteDto> savePaciente(PacienteDto pacienteDto);
   ResponseEntity<PacienteDto> updatePacienteByRg(PacienteDto pacienteDto);
   ResponseEntity<String> deletePaciente(String rg);

}
