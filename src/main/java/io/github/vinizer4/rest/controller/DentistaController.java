package io.github.vinizer4.rest.controller;


import io.github.vinizer4.rest.dto.DentistaDto;
import io.github.vinizer4.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dentistas")
public class DentistaController {

    @Autowired
    private DentistaServiceImpl dentistaService;

    @GetMapping()

    public List<DentistaDto> findAllDentistas(){
        return dentistaService.findAllDenstistas();
    }

    @GetMapping("{matricula}")
    public ResponseEntity<DentistaDto> findByMatricula(@PathVariable String matricula){
        return dentistaService.findByMatricula(matricula);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<DentistaDto> saveDentista(@RequestBody DentistaDto dentistaDto){
        return dentistaService.saveDentista(dentistaDto);
    }

    @PutMapping()
    @ResponseBody
    public ResponseEntity<DentistaDto> updateDentista(@RequestBody @Valid DentistaDto dentistadto){
        return dentistaService.updateDentistaByMatricula(dentistadto);
    }

    @DeleteMapping("{matricula}")
    public ResponseEntity<String> deleteDentista(@PathVariable String matricula){
        return dentistaService.deleteDentista(matricula);
    }
}
