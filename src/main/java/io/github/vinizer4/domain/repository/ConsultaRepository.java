<<<<<<< HEAD:src/main/java/io/github/vinizer4/domain/repository/ConsultaRepository.java
package io.github.vinizer4.domain.repository;


import io.github.vinizer4.domain.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
=======
package com.odonto.clinicaodonto.repositories;


import com.odonto.clinicaodonto.model.Consulta;
import com.odonto.clinicaodonto.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
>>>>>>> 6ef8dc5101433bcc919b99cd5866036af06389e0:src/main/java/com/odonto/clinicaodonto/repositories/ConsultaRepository.java
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
<<<<<<< HEAD:src/main/java/io/github/vinizer4/domain/repository/ConsultaRepository.java

   List<Consulta> findByPacienteRg(String rg);

   List<Consulta> findByDentistaMatricula(String matricula);

   Consulta findByConsultaIdAndPacienteRg(int consultaId, String rg);

   List<Consulta> findByDentistaMatriculaAndDhConsultaBetween(String matricula, LocalDateTime dataHoraDaConsultaIni, LocalDateTime dataHoraDaConsultaFim);
=======
    @Query(nativeQuery = true, value = "select * from consulta c" +
            "WHERE dentista_id = :dentista " +
            "AND `data` = :data"+
            "AND hora = :hora")

    Consulta  findByConsultaByDentistaAndHorario(long dentista, LocalDate data, LocalTime hora);
>>>>>>> 6ef8dc5101433bcc919b99cd5866036af06389e0:src/main/java/com/odonto/clinicaodonto/repositories/ConsultaRepository.java
}
