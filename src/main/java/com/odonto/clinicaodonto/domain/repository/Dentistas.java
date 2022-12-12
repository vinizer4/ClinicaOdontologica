package com.odonto.clinicaodonto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odonto.clinicaodonto.domain.entity.Dentista;

import java.util.List;

@Repository
public interface Dentistas extends JpaRepository<Dentista, Long> {

    @Query(value = " select * from dentista c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Dentista> encontrarPorNome(@Param("nome") String nome );

    @Query(" delete from Dentista c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Dentista c left join fetch c.consulta where c.id = :id  ")
    Dentista findDentistaFetchConsultas( @Param("id") Integer id );
}
