package io.github.vinizer4.service;

import io.github.vinizer4.rest.dto.DentistaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DentistaService {

    List<DentistaDto> findAllDenstistas();
//    ResponseEntity<DentistaDto> findDentistaById(Long id);
    ResponseEntity<DentistaDto> findByMatricula(String matricula);
    ResponseEntity<DentistaDto> saveDentista(DentistaDto dentistaDto);
    ResponseEntity<DentistaDto> updateDentistaByMatricula(DentistaDto dentistaDto);
    ResponseEntity<String> deleteDentista(String matricula);
}
