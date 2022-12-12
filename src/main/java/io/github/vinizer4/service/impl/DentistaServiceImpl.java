package io.github.vinizer4.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.vinizer4.domain.entity.Dentista;
import io.github.vinizer4.domain.repository.DentistaRepository;
import io.github.vinizer4.rest.dto.DentistaDto;
import io.github.vinizer4.service.DentistaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class DentistaServiceImpl implements DentistaService {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private DentistaRepository dentistaRepository;

    @Lazy
    @Autowired
    private ConsultaServiceImpl consultaService;

    @Override
    public List<DentistaDto> findAllDenstistas() {
        log.info("[DentistaService] [findAllDentistas]");
        List<Dentista> dentistas = dentistaRepository.findAll();
        List<DentistaDto> dentistasDto = new ArrayList<>();
        mapper.registerModule(new JavaTimeModule());
        for(Dentista dentista : dentistas) {
            dentistasDto.add(mapper.convertValue(dentista, DentistaDto.class));
        }
        return dentistasDto;
    }

    @Override
    public ResponseEntity<DentistaDto> findByMatricula(String matricula) {
        log.info("[DentistaService] [findByMatricula]");
        mapper.registerModule(new JavaTimeModule());
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(dentistaRepository.findByMatricula(matricula).get(), DentistaDto.class));
        }catch(Exception e){
            return new ResponseEntity("O Dentista não foi localizado.",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DentistaDto> saveDentista(DentistaDto dentistadto) {
        log.info("[DentistaService] [saveDentista]");
        try {
            mapper.registerModule(new JavaTimeModule());

            Optional<Dentista> alreadyExists = dentistaRepository.findByMatricula(dentistadto.getMatricula());
            if(alreadyExists.isEmpty()) {
                Dentista dentista = mapper.convertValue(dentistadto, Dentista.class);
                return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(dentistaRepository.save(dentista), DentistaDto.class));
            }
            log.error("[DentistaService] [saveDentista] matricula "+ dentistadto.getMatricula() + " já cadastrada no sistema");
            return new ResponseEntity( "matricula "+ dentistadto.getMatricula() + " já cadastrada no sistema",HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            log.error("[DentistaService] [saveDentista] Não foi possível salvar o dentista");
            return new ResponseEntity("Não foi possível salvar o dentista "+ dentistadto.getNome(),HttpStatus.BAD_REQUEST);
        }

    }
    @Override
    public ResponseEntity<DentistaDto> updateDentistaByMatricula(DentistaDto dentistadto) {
        log.info("[DentistaService] [updateDentistaById]");
        try{
            Dentista dentistaResponse = responseDentistaByMatricula(dentistadto.getMatricula());
            mapper.registerModule(new JavaTimeModule());
            Dentista dentista = mapper.convertValue(dentistadto,Dentista.class);
            dentista.setId(dentistaResponse.getId());
            return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(dentistaRepository.save(dentista), DentistaDto.class));
        }catch (Exception e){
            log.error("[DentistaService] [updateDentistaById] Erro ao atualizar os dados do dentista.", e);
            return new ResponseEntity("Não foi possivel atualizar o(a) Dentista.",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteDentista(String matricula) {
        log.info("[DentistaService] [deleteDentista]");
        try {
            Dentista dentista = responseDentistaByMatricula(matricula);
            if(consultaService. existeConsultaByMatricula(dentista.getMatricula()).size() > 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existem consultas registradas para: "+ dentista.getNome() +" "+dentista.getSobrenome());

            dentistaRepository.deleteById(dentista.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Dentista excluido com sucesso.");

        }catch (Exception e){
            log.error("[DentistaService] [deleteDentista] Erro ao excluir Dentista", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o Dentista" );
        }
    }


    public Dentista responseDentistaByMatricula(String matricula){
        log.info("[DentistaService] [responseDentistaByRg]");
        return dentistaRepository.findByMatricula(matricula).get();
    }
}
