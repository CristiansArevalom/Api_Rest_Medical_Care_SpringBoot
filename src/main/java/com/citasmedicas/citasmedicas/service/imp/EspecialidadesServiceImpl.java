package com.citasmedicas.citasmedicas.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citasmedicas.citasmedicas.controller.dto.EspecialidadDto;
import com.citasmedicas.citasmedicas.exceptions.EspecialidadDoesntExistExceptions;
import com.citasmedicas.citasmedicas.model.entity.EnumEspecialidad;
import com.citasmedicas.citasmedicas.model.entity.Especialidad;
import com.citasmedicas.citasmedicas.model.repository.EspecialidadRepository;
import com.citasmedicas.citasmedicas.service.EspecialidadesService;
import com.citasmedicas.citasmedicas.util.CommonMapper;

import jakarta.annotation.PostConstruct;

@Service
public class EspecialidadesServiceImpl implements EspecialidadesService {
    
    private final EspecialidadRepository especialidadRepository;
    private final CommonMapper mapper;

    public EspecialidadesServiceImpl(EspecialidadRepository especialidadRepository,
    CommonMapper mapper) {
        this.especialidadRepository = especialidadRepository;
        this.mapper=mapper;
    }

    @PostConstruct // se ejecuta apenas inicia el servidor, llena toda la tabla de especialdiades,
    @Override
    public void fillEspecialidades() {
        // LLENANDO AUTOMATICAMENTE LA TABLA ESPECIALIDAD
        try {
            // Si no existe la especialidad, entonces insertela, si ya existe, no.
            List<Especialidad> especialidades = especialidadRepository.findAll();
            List<String> nombreEspecialidades = especialidades.stream().map(especialidad -> especialidad.getNombre())
                    .collect(Collectors.toList());
            for (EnumEspecialidad enumEspecialidad : EnumEspecialidad.values()) {
                if (!nombreEspecialidades.contains(enumEspecialidad.getNombreEnum())) {
                    Especialidad especialidad = new Especialidad(null, enumEspecialidad);
                    especialidadRepository.save(especialidad);
                }
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error al llenar por defecto especialdiades" + ex);
        }
    }

    @Override
    public List<EspecialidadDto> getEspecialidades() {
        // obteniendo especialidades

        try {
            List<Especialidad> especialidades = especialidadRepository.findAll();
            // llenando DTO
            return especialidades.stream().map(
                    especialidad -> mapper.convertToDto(especialidad, EspecialidadDto.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            throw new RuntimeException("ERROR AL LEER LAS ESPECIALIDADES");
        }
    }

    // preguntar busquedaByNombre
    // Preguntar como hacer que las credenciales de la BD se consuman desde un JSON
    @Override
    public EspecialidadDto getEspecialidadByNombre(String nombre) {
        /*
         * trae el tipo de dato enumEspecialidad en base al string nombre; Primero busca
         * si existe en la clase, ya que se definio que es enum y debe si o si existir
         * en dica clase,
         * //TRY CATCH , si NO existe en enum especialidad arroja error,
         */
        try {
            EnumEspecialidad enumPorNombre = EnumEspecialidad.valueOf(nombre.toUpperCase());
            Optional<Especialidad> especialidad = especialidadRepository.findByNombre(enumPorNombre);
            if (especialidad.isEmpty()) {
                throw new EspecialidadDoesntExistExceptions("No existe la especialidad con excactamente ese nombre");
            }
            return mapper.convertToDto(especialidad, EspecialidadDto.class);
        } catch (IllegalArgumentException ex) {
            throw new EspecialidadDoesntExistExceptions("No existe la especialidad con excactamente ese nombre");
        }
    }

    @Override
    public EspecialidadDto getEspecialidadById(Long id) {
        Optional<Especialidad> especialidad = especialidadRepository.findById(id);
        if (especialidad.isEmpty()) {
            throw new EspecialidadDoesntExistExceptions("No existe la especialidad con ese ID");
        }
        return mapper.convertToDto(especialidad, EspecialidadDto.class);
    }
    
}