package com.odonto.clinicaodonto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odonto.clinicaodonto.domain.entity.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface Pacientes extends JpaRepository<Paciente, Long> {

    @Query(value = " select * from paciente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Paciente> encontrarPacientePorNome(@Param("nome") String nome );

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Paciente c left join fetch c.consulta where c.id = :id  ")
    Paciente findPacienteFetchConsulta( @Param("id") Integer id );

    Optional<Object> findById(Integer id);
}