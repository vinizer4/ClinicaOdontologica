package io.github.vinizer4.service;


import io.github.vinizer4.exception.InvalidRegistrationException;
import io.github.vinizer4.exception.ResourceNotFoundException;
import io.github.vinizer4.rest.dto.ConsultaDto;
import io.github.vinizer4.rest.dto.ConsultaMarcacaoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultaService {

   ResponseEntity<List<ConsultaDto>> findAllConsultas() throws ResourceNotFoundException;
   ResponseEntity<List<ConsultaDto>> findConsultaByRg(String rg) throws ResourceNotFoundException;
   ResponseEntity<ConsultaDto> saveConsulta(ConsultaMarcacaoDto consultaMarcacaoDto) throws InvalidRegistrationException;
   ResponseEntity<ConsultaDto> updateConsultaByRg(ConsultaMarcacaoDto consultaMarcacaoDto) throws InvalidRegistrationException, ResourceNotFoundException;
   ResponseEntity<String> deleteConsulta(ConsultaMarcacaoDto consultaMarcacao) throws ResourceNotFoundException;

}
