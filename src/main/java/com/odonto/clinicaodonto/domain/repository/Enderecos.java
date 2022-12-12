package com.odonto.clinicaodonto.domain.repository;

import com.odonto.clinicaodonto.domain.entity.Dentista;
import com.odonto.clinicaodonto.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.clinicaodonto.domain.entity.Endereco;

import java.util.List;

@Repository
public interface Enderecos extends JpaRepository<Endereco, Long> {

    List<Endereco> findByPaciente(Paciente paciente);

    List<Endereco> findByDentista(Dentista dentista);
 }
