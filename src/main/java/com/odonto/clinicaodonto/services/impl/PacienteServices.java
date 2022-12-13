package com.odonto.clinicaodonto.services.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odonto.clinicaodonto.exception.handler.ResourceNotFoundException;
import com.odonto.clinicaodonto.model.Paciente;
import com.odonto.clinicaodonto.repositories.PacienteRepository;

@Service
public class PacienteServices {

    private Logger logger = Logger.getLogger(PacienteServices.class.getName());

    @Autowired
    PacienteRepository repository;

    public List<Paciente> findAll() {

        logger.info("Procurando Todos Pacientes!");

        return repository.findAll();
    }

    public Paciente findById(Long id) {

        logger.info("Procurando um Paciente!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum Paciente com esse ID! "));
    }

    public Paciente create(Paciente paciente) {
        logger.info("Cadastrando um Paciente!");
        return repository.save(paciente);
    }

    public Paciente update(Paciente paciente) {
        logger.info("Paciente atualizado!");

        var entity = repository.findById(paciente.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID! "));

        entity.setNome(paciente.getNome());
        entity.setSobrenome(paciente.getSobrenome());
        entity.setCpf(paciente.getCpf());
        entity.setData(paciente.getData());
        entity.setEndereco(paciente.getEndereco());

        return repository.save(paciente);
    }

    public void delete(Long id) {
        logger.info("Deletar um Paciente!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há Paciente com esse Id"));

        repository.delete(entity);
    }
}
