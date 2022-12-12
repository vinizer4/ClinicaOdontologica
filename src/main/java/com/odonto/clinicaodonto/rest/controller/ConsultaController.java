package com.odonto.clinicaodonto.rest.controller;

import com.odonto.clinicaodonto.domain.entity.Consulta;
import com.odonto.clinicaodonto.domain.entity.ItemConsulta;
import com.odonto.clinicaodonto.domain.enums.StatusConsulta;
import com.odonto.clinicaodonto.rest.dto.AtualizacaoStatusConsultaDTO;
import com.odonto.clinicaodonto.rest.dto.ConsultaDTO;
import com.odonto.clinicaodonto.rest.dto.InformacaoItemConsultaDTO;
import com.odonto.clinicaodonto.rest.dto.InformacoesConsultaDTO;
import com.odonto.clinicaodonto.service.ConsultaService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody @Valid ConsultaDTO dto ){
        Consulta consulta = service.salvar(dto);
        return consulta.getId();
    }

    @GetMapping("{id}")
    public InformacoesConsultaDTO getById( @PathVariable Integer id ){
        return service
                .obterConsultaCompleta(id)
                .map( p -> converter(p) )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id ,
                             @RequestBody AtualizacaoStatusConsultaDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusConsulta.valueOf(novoStatus));
    }

    private InformacoesConsultaDTO converter(Consulta consulta){
        return InformacoesConsultaDTO
                .builder()
                .codigo(consulta.getId())
                .dataConsulta(consulta.getDataConsulta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(consulta.getPaciente().getCpf())
                .nomePaciente(consulta.getPaciente().getNome())
                .nomeDentista(consulta.getDentista().getNome())
                .dataConsulta(consulta.getDataConsulta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .horaConsulta(consulta.getHoraConsulta().format(DateTimeFormatter.ofPattern("00:00:00")))
                .status(consulta.getStatus().name())
                .total(consulta.getTotal())
                .itens(converter(consulta.getItens()))
                .build();
    }

    private List<InformacaoItemConsultaDTO> converter(List<ItemConsulta> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemConsultaDTO
                        .builder().nomePaciente(item.getPaciente().getNome())
                        .total(item.getTotal())
                        .nomeDentista(item.getDentista().getNome())
                        .nomePaciente(item.getPaciente().getNome())
                        .build()
        ).collect(Collectors.toList());
    }
}
