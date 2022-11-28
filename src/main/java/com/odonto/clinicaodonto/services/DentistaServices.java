package com.odonto.clinicaodonto.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odonto.clinicaodonto.exception.handler.ResourceNotFoundException;
import com.odonto.clinicaodonto.model.Dentista;
import com.odonto.clinicaodonto.repositories.DentistaRepository;

@Service
public class DentistaServices {

    private Logger logger = Logger.getLogger(DentistaServices.class.getName());

    @Autowired
    DentistaRepository repository;

    public List<Dentista> findAll() {

        logger.info("Procurando Todos Dentistas!");

        return repository.findAll();
    }

    public Dentista findById(Long id) {

        logger.info("Procurando um Dentista!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum Dentista com esse ID! "));
    }

    public Dentista create(Dentista dentista) {
        logger.info("Cadastrando um Dentista!");
        return repository.save(dentista);
    }

    public Dentista update(Dentista dentista) {
        logger.info("Atualizando um Dentista!");

        var entity = repository.findById(dentista.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não há Dentista com esse Id! "));

        entity.setNome(dentista.getNome());
        entity.setSobrenome(dentista.getSobrenome());
        entity.setMatricula(dentista.getMatricula());

        return repository.save(dentista);
    }

    public void delete(Long id) {
        logger.info("Deletar um Dentista!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há Dentista com esse Id"));

        repository.delete(entity);
    }
}