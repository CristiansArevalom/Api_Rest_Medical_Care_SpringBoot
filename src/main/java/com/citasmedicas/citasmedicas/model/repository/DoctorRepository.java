package com.citasmedicas.citasmedicas.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.citasmedicas.citasmedicas.model.entity.Doctor;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;


public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findAll();
    Optional<Doctor> findById(Long id);
    Optional<Doctor> findByCorreo(String correo);
    /*Nota: La sintaxis correcta de JPQL requiere que se haga referencia a las entidades
     por su nombre de clase, por lo que es necesario 
     utilizar alias para hacer referencia a los objetos de las entidades en la consulta.*/
   @Query("SELECT doc FROM Doctor doc INNER JOIN Especialidad esp ON doc.especialidad=esp")
    List<Doctor> findAllWithEspecialidad();
    List<Doctor> findAllByEspecialidad(Especialidad especialidad);

}
