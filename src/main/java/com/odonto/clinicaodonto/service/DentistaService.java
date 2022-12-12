package com.odonto.clinicaodonto.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odonto.clinicaodonto.exception.ResourceNotFoundException;
import com.odonto.clinicaodonto.domain.entity.Dentista;
import com.odonto.clinicaodonto.domain.repository.Dentistas;

@Service
public class DentistaService {

    private Logger logger = Logger.getLogger(DentistaService.class.getName());

    @Autowired
    Dentistas repository;

    public List<Dentista> findAll() {

        logger.info("Procurando Todos Dentistas!");

        return repository.findAll();
    }

    public Dentista findById(Integer id) {

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