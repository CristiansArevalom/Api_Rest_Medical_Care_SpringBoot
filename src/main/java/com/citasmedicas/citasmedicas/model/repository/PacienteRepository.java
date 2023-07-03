package com.citasmedicas.citasmedicas.model.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citasmedicas.citasmedicas.model.entity.Paciente;

public interface PacienteRepository extends JpaRepository <Paciente,Long> {
    List<Paciente> findAll();
    Optional<Paciente> findByCedula(String cedula);
    Optional<Paciente> findByTelefono(String telefono );
}
