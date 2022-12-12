package com.odonto.clinicaodonto.service.impl;


import com.odonto.clinicaodonto.domain.entity.Consulta;
import com.odonto.clinicaodonto.domain.entity.Dentista;
import com.odonto.clinicaodonto.domain.entity.ItemConsulta;
import com.odonto.clinicaodonto.domain.entity.Paciente;
import com.odonto.clinicaodonto.domain.enums.StatusConsulta;
import com.odonto.clinicaodonto.domain.repository.*;
import com.odonto.clinicaodonto.exception.ConsultaNaoEncontradaException;
import com.odonto.clinicaodonto.exception.RegraNegocioException;
import com.odonto.clinicaodonto.rest.dto.ConsultaDTO;
import com.odonto.clinicaodonto.rest.dto.ItemConsultaDTO;
import com.odonto.clinicaodonto.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final Consultas repository;
    private final Pacientes pacientesRepository;
    private final Dentistas dentistasRepository;
    private final ItensConsulta itensConsultaRepository;

    private final Enderecos enderecosRepository;

    @Override
    @Transactional
    public Consulta salvar( ConsultaDTO dto ) {

        Integer idDentista = dto.getDentista();
        Dentista dentista = dentistasRepository.findById(Long.valueOf(idDentista)).orElseThrow(() -> new RegraNegocioException("Código de Paciente inválido."));

        Integer idPaciente = dto.getPaciente();
        Paciente paciente = (Paciente) pacientesRepository
                .findById(idPaciente)
                .orElseThrow(() -> new RegraNegocioException("Código de Paciente inválido."));

        Consulta consulta = new Consulta();
        consulta.setTotal(dto.getTotal());
        consulta.setDataConsulta(LocalDate.now());
        consulta.setHoraConsulta(LocalTime.now());
        consulta.setPaciente(paciente);
        consulta.setStatus(StatusConsulta.REALIZADA);

        List<ItemConsulta> itensConsulta = converterItems(consulta, dto.getItems());
        repository.save(consulta);
        itensConsultaRepository.saveAll(itensConsulta);
        consulta.setItens(itensConsulta);
        return consulta;
    }

    @Override
    public Optional<Consulta> obterConsultaCompleta(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus( Integer id, StatusConsulta statusConsulta ) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusConsulta);
                    return repository.save(pedido);
                }).orElseThrow(() -> new ConsultaNaoEncontradaException() );
    }

    private List<ItemConsulta> converterItems(Consulta consulta, List<ItemConsultaDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar uma consulta sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idPaciente = dto.getPaciente();
                    Paciente produto = (Paciente) pacientesRepository
                            .findById(idPaciente)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idPaciente
                                    ));

                    ItemConsulta itemConsulta = new ItemConsulta();

                    return itemConsulta;
                }).collect(Collectors.toList());

    }
}