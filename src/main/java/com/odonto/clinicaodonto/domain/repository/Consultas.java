package com.odonto.clinicaodonto.domain.repository;

import com.odonto.clinicaodonto.domain.entity.Consulta;
import com.odonto.clinicaodonto.domain.entity.Dentista;
import com.odonto.clinicaodonto.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Consultas extends JpaRepository<Consulta, Integer> {

    List<Consulta> findByPaciente(Paciente cliente);

    List<Consulta> findByDentista(Dentista cliente);

    @Query(" select p from Consulta p left join fetch p.paciente where p.id = :id ")
    Optional<Consulta> findByIdFetchItens(@Param("id") Integer id);
}
