package io.github.vinizer4.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.vinizer4.domain.entity.Consulta;
import io.github.vinizer4.domain.entity.Dentista;
import io.github.vinizer4.domain.entity.Paciente;
import io.github.vinizer4.domain.repository.ConsultaRepository;
import io.github.vinizer4.exception.InvalidRegistrationException;
import io.github.vinizer4.exception.ResourceNotFoundException;
import io.github.vinizer4.rest.dto.ConsultaDto;
import io.github.vinizer4.rest.dto.ConsultaMarcacaoDto;
import io.github.vinizer4.service.ConsultaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Log4j2
@Service
public class ConsultaServiceImpl implements ConsultaService {

   @Autowired
   ConsultaRepository consultaRepository;
   @Autowired
   @Lazy
   PacienteServiceImpl pacienteService;
   @Autowired
   DentistaServiceImpl dentistaService;

   ObjectMapper mapper = new ObjectMapper();

   final String CONSULTARETROATIVA = "ATENÇÃO: Não é possível agendar consulta retroativa.";

   @Override
   public ResponseEntity<List<ConsultaDto>> findAllConsultas() throws ResourceNotFoundException {
      log.info("[ConsultaService] [findAllConsultas]");
      mapper.registerModule(new JavaTimeModule());
      List<Consulta> consultas = consultaRepository.findAll();
      List<ConsultaDto> consultasDto = new ArrayList<>();
      for(Consulta consulta: consultas) {
         consultasDto.add(mapper.convertValue(consulta, ConsultaDto.class));
      }
      if(consultasDto.isEmpty())
         throw new ResourceNotFoundException("Não localizamos nenhuma consulta no sistema.");
      return ResponseEntity.status(HttpStatus.OK).body(consultasDto);
   }

   @Override
   public ResponseEntity<List<ConsultaDto>> findConsultaByRg(String rg) throws ResourceNotFoundException {
      log.info("[ConsultaService] [findConsultaByRg]");
      mapper.registerModule(new JavaTimeModule());
      List<Consulta> consultas = consultaRepository.findByPacienteRg(rg);
      List<ConsultaDto> consultasDto = new ArrayList<>();
      for(Consulta consulta : consultas){
         consultasDto.add(mapper.convertValue(consulta,ConsultaDto.class));
      }
      if(consultasDto.isEmpty())
         throw new ResourceNotFoundException("Não localizamos as consultas do(a) paciente: "+rg);
      return ResponseEntity.status(HttpStatus.OK).body(consultasDto);
   }

   @Override
   public ResponseEntity<ConsultaDto> saveConsulta(ConsultaMarcacaoDto consultaMarcacao) throws InvalidRegistrationException {
      log.info("[ConsultaService] [saveConsulta]");
      if(LocalDateTime.now().isAfter(consultaMarcacao.getDhConsulta()))
         throw new InvalidRegistrationException(CONSULTARETROATIVA);
      Paciente paciente = new Paciente();
      Dentista dentista = new Dentista();
      mapper.registerModule(new JavaTimeModule());
      try{
         paciente = pacienteService.responsePacienteByRg(consultaMarcacao.getRgPaciente());
         dentista = dentistaService.responseDentistaByMatricula(consultaMarcacao.getMatriculaDentista());
         List<ConsultaDto> listaDeConsulta = findConsultaByRg(consultaMarcacao.getRgPaciente()).getBody();
         for(ConsultaDto consultaDto : listaDeConsulta){
            LocalDateTime dhConsultaDaLista = consultaDto.getDhConsulta();
            LocalDateTime dhEnviado = consultaMarcacao.getDhConsulta();
            if(dhConsultaDaLista.minusMinutes(30).isBefore(dhEnviado) && dhConsultaDaLista.plusMinutes(30).isAfter(dhEnviado) || consultaDto.getDhConsulta().isEqual(consultaMarcacao.getDhConsulta()))
               return new ResponseEntity("O Paciente já possui uma consulta neste horário, favor respeitar um intervalo de 30 minutos..",HttpStatus.BAD_REQUEST);
            List<Consulta> dentistaTemConsulta =  responseConsultaByDentistaAndDhConsulta(consultaMarcacao.getMatriculaDentista(),consultaMarcacao.getDhConsulta().minusMinutes(30),consultaMarcacao.getDhConsulta().plusMinutes(30));
            if(dentistaTemConsulta.size() > 0)
               return new ResponseEntity("O Dentista "+dentista.getMatricula()+ ", " + dentista.getNome()+" "+ dentista.getSobrenome()+" já possui uma consulta neste horário.",HttpStatus.BAD_REQUEST);
         }

         Consulta consulta = new Consulta();
         consulta.setConsultaId(listaDeConsulta.size() + 1);
         consulta.setDhConsulta(consultaMarcacao.getDhConsulta());
         consulta.setPaciente(paciente);
         consulta.setDentista(dentista);
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(consultaRepository.save(consulta), ConsultaDto.class));
      }catch (Exception e){
         log.error("[ConsultaService] [saveConsulta] Não foi possível agendar a consulta.");
         if(isNull(paciente.getRg()))
            throw new InvalidRegistrationException("Não foi possível agendar a sua consulta.\nSeu cadastro não foi encontrado.");
         if(isNull(dentista.getMatricula()))
            throw  new InvalidRegistrationException("Não foi possível agendar a sua consulta.\nO dentista informado não foi localizado.");
         throw new InvalidRegistrationException("Não foi possível agendar a sua consulta, favor verificar os dados informado.");
      }
   }

   @Override
   public ResponseEntity<ConsultaDto> updateConsultaByRg(ConsultaMarcacaoDto consultaMarcacao) throws InvalidRegistrationException, ResourceNotFoundException {
      log.info("[ConsultaService] [updateConsultaByRg]");
      if(LocalDateTime.now().isAfter(consultaMarcacao.getDhConsulta()))
         throw new InvalidRegistrationException(CONSULTARETROATIVA);
      Paciente paciente = new Paciente();
      Dentista dentista = new Dentista();
      Consulta consultaAtualizada = new Consulta();
      mapper.registerModule(new JavaTimeModule());
      try{
         List<ConsultaDto> listaDeConsulta = findConsultaByRg(consultaMarcacao.getRgPaciente()).getBody();
         for(ConsultaDto consultaDto : listaDeConsulta){
            LocalDateTime dhConsultaDaLista = consultaDto.getDhConsulta();
            LocalDateTime dhEnviado = consultaMarcacao.getDhConsulta();
            if(dhConsultaDaLista.minusMinutes(30).isBefore(dhEnviado) && dhConsultaDaLista.plusMinutes(30).isAfter(dhEnviado) || consultaDto.getDhConsulta().isEqual(consultaMarcacao.getDhConsulta()))
               return new ResponseEntity("O Paciente já possui uma consulta neste horário, favor respeitar um intervalo de 30 minutos.",HttpStatus.BAD_REQUEST);
            List<Consulta> dentistaTemConsulta =  responseConsultaByDentistaAndDhConsulta(consultaMarcacao.getMatriculaDentista(),consultaMarcacao.getDhConsulta().minusMinutes(30),consultaMarcacao.getDhConsulta().plusMinutes(30));
            if(dentistaTemConsulta.size() > 0)
               return new ResponseEntity("O Dentista "+dentista.getMatricula()+ ", " + dentista.getNome()+" "+ dentista.getSobrenome()+" já possui uma consulta neste horário.",HttpStatus.BAD_REQUEST);
         }
         Consulta consulta = responseConsultaByConsultaIdRg(consultaMarcacao.getConsultaId(),consultaMarcacao.getRgPaciente());
         dentista = dentistaService.responseDentistaByMatricula(consultaMarcacao.getMatriculaDentista());
         paciente = pacienteService.responsePacienteByRg(consultaMarcacao.getRgPaciente());
         consultaAtualizada.setId(consulta.getId());
         consultaAtualizada.setConsultaId(consultaMarcacao.getConsultaId());
         consultaAtualizada.setDhConsulta(consultaMarcacao.getDhConsulta());
         consultaAtualizada.setPaciente(paciente);
         consultaAtualizada.setDentista(dentista);
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(consultaRepository.save(consultaAtualizada),ConsultaDto.class));
      }catch (Exception e){
         log.error("[ConsultaService] [updateConsultaById] Erro ao atualizar a consulta", e);
         throw new ResourceNotFoundException("Não localizamos a consulta do(a) paciente: "+consultaMarcacao.getRgPaciente());
      }
   }

   @Override
   public ResponseEntity<String> deleteConsulta(ConsultaMarcacaoDto consultaMarcacao) throws ResourceNotFoundException {
      log.info("[ConsultaService] [deleteConsulta]");
      Consulta consulta = responseConsultaByConsultaIdRg(consultaMarcacao.getConsultaId(),consultaMarcacao.getRgPaciente());
      if(isNull(consulta))
         throw  new ResourceNotFoundException("Não encontramos consulta do paciente: "+ consultaMarcacao.getRgPaciente());
      consultaRepository.deleteById(consulta.getId());
      return ResponseEntity.status(HttpStatus.OK).body("A consulta do(a) paciente " + consulta.getPaciente().getNome()+" "+ consulta.getPaciente().getSobrenome()+", no dia "+ consulta.getDhConsulta()+" foi excluida com sucesso.");
   }

   public Consulta responseConsultaByConsultaIdRg(int consultaId, String rg){
      log.info("[ConsultaService] [responseConsultaByRg]");
      return consultaRepository.findByConsultaIdAndPacienteRg(consultaId,rg);
   }

   public List<Consulta> existeConsultaByRg(String rg){
      log.info("[ConsultaService] [existeConsultaByRg]");
      return consultaRepository.findByPacienteRg(rg);
   }

   public List<Consulta> existeConsultaByMatricula(String matricula){
      log.info("[ConsultaService] [existeConsultaByMatricula]");
      return consultaRepository.findByDentistaMatricula(matricula);
   }

   public List<Consulta> responseConsultaByDentistaAndDhConsulta(String matricula, LocalDateTime dataHoraDaConsultaIni, LocalDateTime dataHoraDaConsultaFim){
      log.info("[ConsultaService] [responseConsultaByDentistaAndDhConsulta]");
      return consultaRepository.findByDentistaMatriculaAndDhConsultaBetween(matricula,dataHoraDaConsultaIni,dataHoraDaConsultaFim);
   }

}
