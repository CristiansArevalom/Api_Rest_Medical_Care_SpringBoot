package com.citasmedicas.citasmedicas.model.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citasmedicas.citasmedicas.model.entity.Consultorio;

public interface ConsultorioRepository extends JpaRepository<Consultorio,Long> {
    List<Consultorio> findAll();
    Optional<Consultorio> findById(Long id);
    List<Consultorio> findByDireccion(String direccion);
    //Consulta que ayuda a validar que sea exactamente el mismo consultorio
    List<Consultorio> findByCiudadAndDescripcionAndDireccionAndNumero(String ciudad,String descripcion,String direccion,Integer numero);
    //BUSCARConsultoriosLibres 
/*
@Query("SELECT c FROM Consultorio c " +
        "WHERE c.id NOT IN (" +
        "SELECT ca.consultorio.id FROM ConsultorioAsignado ca " +
        "WHERE ca.inicioReserva <= :fechaInicio AND ca.finReserva >= :fechaFin " +
        "GROUP BY ca.consultorio.id)")
  */

@Query("SELECT c FROM Consultorio c " +
        "WHERE c.id NOT IN (" +
        "SELECT ca.consultorio.id FROM ConsultorioAsignado ca " +
        "WHERE (ca.inicioReserva <= :fechaInicio AND ca.finReserva >= :fechaInicio)" +
        "OR (ca.inicioReserva <= :fechaFin AND ca.finReserva >= :fechaFin)"+
        "GROUP BY ca.consultorio.id)")
       List<Consultorio> obtenerConsultoriosDisponibles(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
    
}
