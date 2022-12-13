package io.github.vinizer4.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.vinizer4.domain.entity.Paciente;
import io.github.vinizer4.domain.repository.PacienteRepository;
import io.github.vinizer4.rest.dto.PacienteDto;
import io.github.vinizer4.service.PacienteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class PacienteServiceImpl implements PacienteService {

   @Autowired
   private PacienteRepository pacienteRepository;

   @Autowired
   private ConsultaServiceImpl consultaService;

   ObjectMapper mapper = new ObjectMapper();

   @Override
   public ResponseEntity<List<PacienteDto>> findAllPacientes() {
      log.info("[PacienteService] [findAllPacientes]");
      mapper.registerModule(new JavaTimeModule());
      try{
         List<Paciente> pacientes = pacienteRepository.findAll();
         List<PacienteDto> pacientesDto = new ArrayList<>();
         for(Paciente paciente : pacientes) {
            pacientesDto.add(mapper.convertValue(paciente, PacienteDto.class));
         }
         if(pacientesDto.isEmpty())
            return new ResponseEntity("Não localizamos nenhum paciente no sistema.",HttpStatus.BAD_REQUEST);
         return ResponseEntity.status(HttpStatus.OK).body(pacientesDto);
      }catch (Exception e){
         return new ResponseEntity("Erro ao buscar pacientes.",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<PacienteDto> findByRg(String rg) {
      log.info("[PacienteService] [findByRg]");
      mapper.registerModule(new JavaTimeModule());
      try{
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.findByRg(rg).get(),PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [findPacienteById] Paciente não localizado");
         return new ResponseEntity("Paciente não foi localizado",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   @Transactional
   public ResponseEntity<PacienteDto> savePaciente(PacienteDto pacienteDto) {
      log.info("[PacienteService] [savePaciente]");
      try{
         mapper.registerModule(new JavaTimeModule());
         pacienteDto.setDataCadastro(LocalDate.now());
         Paciente paciente = mapper.convertValue(pacienteDto,Paciente.class);
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(pacienteRepository.save(paciente),PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [savePaciente] Não foi possível salvar o paciente");
         return new ResponseEntity("Não foi possível salvar o paciente "+ pacienteDto.getNome(),HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   @Transactional
   public ResponseEntity <PacienteDto> updatePacienteByRg(PacienteDto pacienteDto) {
      log.info("[PacienteService] [updatePacienteByRg]");
      try{
         Paciente pacienteResponse = responsePacienteByRg(pacienteDto.getRg());
         mapper.registerModule(new JavaTimeModule());
         Paciente paciente = mapper.convertValue(pacienteDto,Paciente.class);
         paciente.setId(pacienteResponse.getId());
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [updatePacienteById] Erro ao atualizar os dados do paciente: "+pacienteDto.getRg(), e);
         return new ResponseEntity("Não foi possivel atualizar o(a) Paciente: "+pacienteDto.getRg(),HttpStatus.BAD_REQUEST);
      }
   }

   @Override
      public ResponseEntity<String> deletePaciente(String rg) {
      log.info("[PacienteService] [deletePaciente]");
      try {
         Paciente paciente =  responsePacienteByRg(rg);
         if(consultaService.existeConsultaByRg(paciente.getRg()).size() > 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existem consultas registradas para: "+ paciente.getNome() +" "+paciente.getSobrenome());

            pacienteRepository.deleteById(paciente.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Paciente " + paciente.getNome() +" excluido com sucesso.");
      }catch (Exception e){
         log.error("[PacienteService] [deletePaciente] Não foi possível excluir o Paciente", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O paciente: "+rg + " não foi localizado.");
      }
   }

   public Paciente responsePacienteByRg(String rg){
      log.info("[PacienteService] [responsePacienteByRg]");
      return pacienteRepository.findByRg(rg).get();
   }


}
