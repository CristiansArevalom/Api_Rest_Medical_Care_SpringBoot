package com.citasmedicas.citasmedicas.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.citasmedicas.citasmedicas.model.entity.CitaMedica;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;

public interface CitaMedicaRepository extends JpaRepository <CitaMedica,Long>{
    List<CitaMedica> findAll();
    Optional<CitaMedica> findById(Long id);
    List<CitaMedica> findAllByPacienteId(Long id);
    List<CitaMedica> findAllByConsultorioAsignadoId(Long id);
    List<CitaMedica> findAllByConsultorioAsignadoDoctorId(Long id);
    List<CitaMedica> findAllByConsultorioAsignadoDoctorEspecialidad(Especialidad especialidad);

    /* OK, TAMBIEN SE PUEDE USAR @QUERY PERO POR SEGURIDAD ES MEJOR DEJARLE LA RESPONSABILIDAD A JPA
    @Query("SELECT c FROM CitaMedica c " +
           "INNER JOIN c.consultorioAsignado ca " +
           "INNER JOIN ca.doctor d " +
           "WHERE d.id = :doctorId")
    List<CitaMedica> findAllByDoctorId(@Param("doctorId") Long id);
   
    @TODO, PDT HACER QUERY FUNCIONAR
    @Query("SELECT c FROM CitasMedicas c " +
           "INNER JOIN c.consultorioAsignado ca " +
           "INNER JOIN ca.doctor d " +
           "INNER JOIN d.especialidad esp " +
           "WHERE esp = :especialidad")
    List<CitaMedica> findAllByEspecialidad(@Param("especialidad")Especialidad especialidad);
         */

   
 

}
