package com.odonto.clinicaodonto.services.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odonto.clinicaodonto.exception.handler.ResourceNotFoundException;
import com.odonto.clinicaodonto.model.Endereco;
import com.odonto.clinicaodonto.repositories.EnderecoRepository;

@Service
public class EnderecoServices {

    private Logger logger = Logger.getLogger(EnderecoServices.class.getName());

    @Autowired
    EnderecoRepository repository;

    public List<Endereco> findAll() {

        logger.info("Procurando Todos Enderecos!");

        return repository.findAll();
    }

    public Endereco findById(Long id) {

        logger.info("Procurando um Endereço!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum Endereço com esse ID! "));
    }

    public Endereco create(Endereco endereco) {
        logger.info("Cadastrando um Endereço!");
        return repository.save(endereco);
    }

    public Endereco update(Endereco endereco) {
        logger.info("Atualizando um Endereço!");

        var entity = repository.findById(endereco.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não há Endereco com esse Id"));

        entity.setRua(endereco.getRua());
        entity.setNumero(endereco.getNumero());
        entity.setBairro(endereco.getBairro());
        entity.setCidade(endereco.getCidade());

        return repository.save(endereco);
    }

    public void delete(Long id) {
        logger.info("Deletar um Dentista!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há Endereço com esse Id"));

        repository.delete(entity);
    }
}