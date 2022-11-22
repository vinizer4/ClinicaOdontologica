package com.odonto.clinicaodonto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odonto.clinicaodonto.model.Dentista;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
