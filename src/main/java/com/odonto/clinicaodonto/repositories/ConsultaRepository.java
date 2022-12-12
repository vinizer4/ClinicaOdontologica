package com.odonto.clinicaodonto.repositories;


import com.odonto.clinicaodonto.model.Consulta;
import com.odonto.clinicaodonto.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query(nativeQuery = true, value = "select * from consulta c" +
            "WHERE dentista_id = :dentista " +
            "AND `data` = :data"+
            "AND hora = :hora")

    Consulta  findByConsultaByDentistaAndHorario(long dentista, LocalDate data, LocalTime hora);
}
