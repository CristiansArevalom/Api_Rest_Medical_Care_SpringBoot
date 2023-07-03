package com.citasmedicas.citasmedicas.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad,Long>{
    List<Especialidad> findAll();
    Optional <Especialidad> findByNombre(EnumEspecialidad nombre);
    Optional<Especialidad> findById(Long id);
    List<Especialidad> findByNombreContaining(EnumEspecialidad nombre); //BUSQUEDA LIKE %MEDICINA%
    @Query("SELECT e FROM Especialidad e WHERE e.nombre LIKE %:nombre%")
    List<Especialidad> findByNombreLike(@Param("nombre") String nombre);

}
