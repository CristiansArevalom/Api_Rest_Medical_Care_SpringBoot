package com.citasmedicas.citasmedicas.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.citasmedicas.citasmedicas.model.entity.ConsultorioAsignado;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;

public interface ConsultorioAsignadoRepository extends JpaRepository<ConsultorioAsignado,Long>{

    List<ConsultorioAsignado> findAll();
    List<ConsultorioAsignado> findAllByConsultorioId(Long id);
    List<ConsultorioAsignado> findAllByDoctorId(Long id);
    List<ConsultorioAsignado> findAllByInicioReservaLessThanEqualAndFinReservaGreaterThanEqual(LocalDateTime inicioReserva,LocalDateTime finReserva);
    List<ConsultorioAsignado> findAllByDoctorEspecialidad(Especialidad especialidad);
    
}
