package io.github.vinizer4.domain.repository;

import io.github.vinizer4.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

   Optional<Paciente> findByRg(String rg);
}
