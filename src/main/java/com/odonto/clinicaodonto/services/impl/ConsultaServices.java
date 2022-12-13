package com.odonto.clinicaodonto.services.impl;

import com.odonto.clinicaodonto.dto.ConsultaDTO;
import com.odonto.clinicaodonto.exception.handler.ResourceNotFoundException;
import com.odonto.clinicaodonto.model.Consulta;
import com.odonto.clinicaodonto.model.Dentista;
import com.odonto.clinicaodonto.model.Paciente;
import com.odonto.clinicaodonto.services.IConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;



@RequiredArgsConstructor
@Service
public class ConsultaServices implements IConsultaService {


    private Consulta repository;
    private Paciente pacienteRepository;
    private Dentista dentistaRepository;


    @Override
    public Consulta salvar(ConsultaDTO dto) {

        Long idPaciente = dto.getPaciente();
        Paciente paciente = pacienteRepository
                .findById(idPaciente)
                .orElseThrow(() -> new ResourceNotFoundException("Código de paciente inválido."));

        Long idDentista = dto.getDentista();
        Dentista dentista = dentistaRepository
                .findById(idDentista)
                .orElseThrow(() -> new ResourceNotFoundException("Código de dentista inválido."));

        Consulta consulta = new Consulta();
        consulta.setData(dto.getData());
        consulta.setHora(dto.getHora());
        consulta.setPaciente(paciente);
        consulta.setDentista(dentista);

        repository.save(consulta);

        return consulta;
    }
}
